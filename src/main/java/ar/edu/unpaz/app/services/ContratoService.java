package ar.edu.unpaz.app.services;

import ar.edu.unpaz.app.model.*;
import ar.edu.unpaz.app.model.strategy.EstadoDisponible;
import ar.edu.unpaz.app.repositories.ContratoRepository;
import ar.edu.unpaz.app.repositories.CuotaMensualRepository;
import ar.edu.unpaz.app.repositories.InmuebleRepository;
import ar.edu.unpaz.app.repositories.PersonaRepository;
import ar.edu.unpaz.app.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar Contratos.
 * Incluye lógica de creación de contrato con validación de estado y generación automática de cuotas.
 */
@Service
@Transactional
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private CuotaMensualRepository cuotaMensualRepository;

    @Autowired
    private InmuebleRepository inmuebleRepository;

    @Autowired
    private PersonaRepository personaRepository;

    /**
     * Crea un contrato verificando que el inmueble esté disponible.
     * Si lo está: invoca inmueble.alquilar(), genera cuotas y persiste todo.
     */
    public Contrato crearContrato(Long inmuebleId, Long propietarioId, Long inquilinoId, 
                                  LocalDate fechaInicio, LocalDate fechaFin, 
                                  BigDecimal montoBase) {
        return crearContrato(inmuebleId, propietarioId, inquilinoId, null, 
                            fechaInicio, fechaFin, montoBase);
    }

    /**
     * Crea un contrato con garante opcional.
     */
    public Contrato crearContrato(Long inmuebleId, Long propietarioId, Long inquilinoId, 
                                  Long garanteId, LocalDate fechaInicio, LocalDate fechaFin, 
                                  BigDecimal montoBase) {
        // Validaciones básicas
        if (inmuebleId == null || inmuebleId <= 0) {
            throw new InvalidIdException("ID de inmueble inválido");
        }
        if (propietarioId == null || propietarioId <= 0) {
            throw new InvalidIdException("ID de propietario inválido");
        }
        if (inquilinoId == null || inquilinoId <= 0) {
            throw new InvalidIdException("ID de inquilino inválido");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new InvalidFechasException("Fechas no pueden ser null");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new InvalidFechasException("Fecha fin debe ser posterior a fecha inicio");
        }
        if (montoBase == null || montoBase.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidMontoException("Monto base debe ser positivo");
        }

        // Obtener inmueble y validar su disponibilidad
        Inmueble inmueble = inmuebleRepository.findById(inmuebleId)
                .orElseThrow(() -> new InmuebleNotFoundException("Inmueble con ID " + inmuebleId + " no encontrado"));

        if (!(inmueble.getEstado() instanceof EstadoDisponible)) {
            throw new InmuebleNotDisponibleException("El inmueble no está disponible. Estado actual: " + inmueble.getNombreEstado());
        }

        // Obtener Personas
        Persona propietario = personaRepository.findById(propietarioId)
                .orElseThrow(() -> new PersonaNotFoundException("Propietario con ID " + propietarioId + " no encontrado"));

        Persona inquilino = personaRepository.findById(inquilinoId)
                .orElseThrow(() -> new PersonaNotFoundException("Inquilino con ID " + inquilinoId + " no encontrado"));

        Persona garante = null;
        if (garanteId != null && garanteId > 0) {
            garante = personaRepository.findById(garanteId)
                    .orElseThrow(() -> new PersonaNotFoundException("Garante con ID " + garanteId + " no encontrado"));
        }

        // Cambiar estado del inmueble a alquilado
        inmueble.alquilar();
        inmuebleRepository.save(inmueble);

        // Crear la entidad Contrato
        Contrato contrato = new Contrato();
        contrato.setInmueble(inmueble);
        contrato.setPropietario(propietario);
        contrato.setInquilino(inquilino);
        contrato.setGarante(garante);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFin(fechaFin);
        contrato.setMontoBase(montoBase);

        // Generar cuotas mensuales automáticamente
        List<CuotaMensual> cuotas = generarCuotasAutomaticas(contrato, fechaInicio, fechaFin, montoBase);
        contrato.setCuotas(cuotas);

        // Guardar contrato (y cuotas en cascade)
        return contratoRepository.save(contrato);
    }

    /**
     * Genera automáticamente las cuotas mensuales basadas en las fechas del contrato.
     * Distribuye el montoBase entre los meses.
     */
    private List<CuotaMensual> generarCuotasAutomaticas(Contrato contrato, LocalDate fechaInicio, 
                                                         LocalDate fechaFin, BigDecimal montoBase) {
        List<CuotaMensual> cuotas = new ArrayList<>();

        // Calcular cantidad de meses
        YearMonth mesInicio = YearMonth.from(fechaInicio);
        YearMonth mesFin = YearMonth.from(fechaFin);

        int cantidadMeses = (int) java.time.temporal.ChronoUnit.MONTHS.between(mesInicio, mesFin) + 1;
        if (cantidadMeses < 1) cantidadMeses = 1;

        // Distribuir monto entre cuotas
        BigDecimal montoPorCuota = montoBase.divide(BigDecimal.valueOf(cantidadMeses), 2, java.math.RoundingMode.HALF_UP);

        // Generar cuotas mensuales
        LocalDate fechaVencimiento = fechaInicio;
        for (int i = 0; i < cantidadMeses; i++) {
            CuotaMensual cuota = new CuotaMensual();
            cuota.setContrato(contrato);
            cuota.setMontoTotal(montoPorCuota);
            cuota.setFechaVencimiento(fechaVencimiento);
            cuota.setEstado(CuotaMensualEstado.PENDIENTE);
            cuotas.add(cuota);

            // Pasar al siguiente mes
            fechaVencimiento = fechaVencimiento.plusMonths(1);
        }

        return cuotas;
    }

    public List<Contrato> obtenerTodos() {
        return contratoRepository.findAll();
    }

    public Optional<Contrato> obtenerPorId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException("ID inválido");
        }
        return contratoRepository.findById(id);
    }

    public List<Contrato> obtenerPorInmueble(Long inmuebleId) {
        Inmueble inmueble = inmuebleRepository.findById(inmuebleId)
                .orElseThrow(() -> new InmuebleNotFoundException("Inmueble con ID " + inmuebleId + " no encontrado"));
        return contratoRepository.findByInmueble(inmueble);
    }
}


