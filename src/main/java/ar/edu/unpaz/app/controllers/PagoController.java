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

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    public static class RegistrarPagoRequest {
        public Long cuotaId;
        public BigDecimal monto;
        public String metodoPago;
        public String nroComprobante;
    }

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

