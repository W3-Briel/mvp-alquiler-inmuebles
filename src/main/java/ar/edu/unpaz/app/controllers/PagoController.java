package ar.edu.unpaz.app.controllers;

import ar.edu.unpaz.app.model.Pago;
import ar.edu.unpaz.app.services.PagoService;
import ar.edu.unpaz.app.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controlador REST para la gestión de Pagos.
 */
@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    /**
     * DTO para registrar un pago
     */
    public static class RegistrarPagoRequest {
        public Long cuotaId;
        public BigDecimal monto;
        public String metodoPago;
        public String nroComprobante;
    }

    /**
     * POST /api/pagos - Registrar un pago a una cuota
     * Si el cuota queda completamente paga, su estado cambia a PAGADA automáticamente.
     */
    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody RegistrarPagoRequest request) {
        try {
            Pago pago = pagoService.registrarPago(
                    request.cuotaId,
                    request.monto,
                    request.metodoPago,
                    request.nroComprobante
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(pago);
        } catch (InvalidCuotaIdException | InvalidMontoException | InvalidMethodoPagoException |
                 InvalidComprobanteException | CuotaNotFoundException | CuotaYaPagadaException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/pagos/cuota/{cuotaId} - Obtener todos los pagos de una cuota
     */
    @GetMapping("/cuota/{cuotaId}")
    public ResponseEntity<List<Pago>> obtenerPagosPorCuota(@PathVariable Long cuotaId) {
        try {
            List<Pago> pagos = pagoService.obtenerPagosPorCuota(cuotaId);
            return ResponseEntity.ok(pagos);
        } catch (InvalidCuotaIdException | CuotaNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

