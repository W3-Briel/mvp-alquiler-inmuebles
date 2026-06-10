package ar.edu.unpaz.app.services;

import ar.edu.unpaz.app.model.CuotaMensual;
import ar.edu.unpaz.app.model.CuotaMensualEstado;
import ar.edu.unpaz.app.model.Pago;
import ar.edu.unpaz.app.repositories.CuotaMensualRepository;
import ar.edu.unpaz.app.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Servicio para gestionar Pagos y Cobranza.
 */
@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private CuotaMensualRepository cuotaMensualRepository;

    /**
     * Registra un pago a una cuota específica.
     * Valida que la cuota exista, crea el pago, y actualiza el estado de la cuota si está completamente paga.
     */
    public Pago registrarPago(Long cuotaId, BigDecimal monto, String metodoPago, String nroComprobante) {
        // Validaciones
        if (cuotaId == null || cuotaId <= 0) {
            throw new IllegalArgumentException("ID de cuota inválido");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monto del pago debe ser positivo");
        }
        if (metodoPago == null || metodoPago.isBlank()) {
            throw new IllegalArgumentException("Método de pago no puede ser null o vacío");
        }
        if (nroComprobante == null || nroComprobante.isBlank()) {
            throw new IllegalArgumentException("Número de comprobante no puede ser null o vacío");
        }

        // Obtener la cuota
        CuotaMensual cuota = cuotaMensualRepository.findById(cuotaId)
                .orElseThrow(() -> new IllegalArgumentException("Cuota con ID " + cuotaId + " no encontrada"));

        // Verificar que la cuota no esté ya pagada
        if (cuota.getEstado() == CuotaMensualEstado.PAGADA) {
            throw new IllegalStateException("La cuota ya está pagada");
        }

        // Crear el pago
        Pago pago = new Pago();
        pago.setCuota(cuota);
        pago.setMontoAbonado(monto);
        pago.setFechaPago(LocalDate.now());
        pago.setMetodoPago(metodoPago);
        pago.setNroComprobante(nroComprobante);

        // Guardar el pago
        Pago pagGuardado = pagoRepository.save(pago);

        // Calcular el total de pagos de esta cuota
        BigDecimal totalPagado = calcularTotalPagado(cuota);

        // Verificar si la cuota está completamente paga
        if (totalPagado.compareTo(cuota.getMontoTotal()) >= 0) {
            cuota.setEstado(CuotaMensualEstado.PAGADA);
            cuotaMensualRepository.save(cuota);
        }

        return pagGuardado;
    }

    /**
     * Calcula el total de pagos realizados para una cuota.
     */
    public BigDecimal calcularTotalPagado(CuotaMensual cuota) {
        if (cuota == null || cuota.getId() == null) {
            throw new IllegalArgumentException("Cuota inválida");
        }
        List<Pago> pagos = pagoRepository.findByCuota(cuota);
        return pagos.stream()
                .map(Pago::getMontoAbonado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Obtiene todos los pagos de una cuota.
     */
    public List<Pago> obtenerPagosPorCuota(Long cuotaId) {
        if (cuotaId == null || cuotaId <= 0) {
            throw new IllegalArgumentException("ID de cuota inválido");
        }
        CuotaMensual cuota = cuotaMensualRepository.findById(cuotaId)
                .orElseThrow(() -> new IllegalArgumentException("Cuota con ID " + cuotaId + " no encontrada"));
        return pagoRepository.findByCuota(cuota);
    }
}

