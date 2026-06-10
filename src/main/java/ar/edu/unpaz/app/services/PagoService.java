package ar.edu.unpaz.app.services;

import ar.edu.unpaz.app.model.CuotaMensual;
import ar.edu.unpaz.app.model.CuotaMensualEstado;
import ar.edu.unpaz.app.model.Pago;
import ar.edu.unpaz.app.repositories.CuotaMensualRepository;
import ar.edu.unpaz.app.repositories.PagoRepository;
import ar.edu.unpaz.app.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private CuotaMensualRepository cuotaMensualRepository;

    public Pago registrarPago(Long cuotaId, BigDecimal monto, String metodoPago, String nroComprobante) {
        if (cuotaId == null || cuotaId <= 0) {
            throw new InvalidCuotaIdException("ID de cuota inválido");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidMontoException("Monto del pago debe ser positivo");
        }
        if (metodoPago == null || metodoPago.isBlank()) {
            throw new InvalidMethodoPagoException("Método de pago no puede ser null o vacío");
        }
        if (nroComprobante == null || nroComprobante.isBlank()) {
            throw new InvalidComprobanteException("Número de comprobante no puede ser null o vacío");
        }

        CuotaMensual cuota = cuotaMensualRepository.findById(cuotaId)
                .orElseThrow(() -> new CuotaNotFoundException("Cuota con ID " + cuotaId + " no encontrada"));

        if (cuota.getEstado() == CuotaMensualEstado.PAGADA) {
            throw new CuotaYaPagadaException("La cuota ya está pagada");
        }

        Pago pago = new Pago();
        pago.setCuota(cuota);
        pago.setMontoAbonado(monto);
        pago.setFechaPago(LocalDate.now());
        pago.setMetodoPago(metodoPago);
        pago.setNroComprobante(nroComprobante);

        Pago pagGuardado = pagoRepository.save(pago);

        BigDecimal totalPagado = calcularTotalPagado(cuota);

        if (totalPagado.compareTo(cuota.getMontoTotal()) >= 0) {
            cuota.setEstado(CuotaMensualEstado.PAGADA);
            cuotaMensualRepository.save(cuota);
        }

        return pagGuardado;
    }

    public BigDecimal calcularTotalPagado(CuotaMensual cuota) {
        if (cuota == null || cuota.getId() == null) {
            throw new IllegalArgumentException("Cuota inválida");
        }
        List<Pago> pagos = pagoRepository.findByCuota(cuota);
        return pagos.stream()
                .map(Pago::getMontoAbonado)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Pago> obtenerPagosPorCuota(Long cuotaId) {
        if (cuotaId == null || cuotaId <= 0) {
            throw new InvalidCuotaIdException("ID de cuota inválido");
        }
        CuotaMensual cuota = cuotaMensualRepository.findById(cuotaId)
                .orElseThrow(() -> new CuotaNotFoundException("Cuota con ID " + cuotaId + " no encontrada"));
        return pagoRepository.findByCuota(cuota);
    }
}

