package ar.edu.unpaz.app.controllers;

import ar.edu.unpaz.app.model.Contrato;
import ar.edu.unpaz.app.services.ContratoService;
import ar.edu.unpaz.app.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    public static class CrearContratoRequest {
        public Long inmuebleId;
        public Long propietarioId;
        public Long inquilinoId;
        public Long garanteId;
        public LocalDate fechaInicio;
        public LocalDate fechaFin;
        public BigDecimal montoBase;
    }

    @PostMapping
    public ResponseEntity<Contrato> crearContrato(@RequestBody CrearContratoRequest request) {
        try {
            Contrato contrato;
            if (request.garanteId != null) {
                contrato = contratoService.crearContrato(
                        request.inmuebleId, request.propietarioId, request.inquilinoId, request.garanteId,
                        request.fechaInicio, request.fechaFin, request.montoBase
                );
            } else {
                contrato = contratoService.crearContrato(
                        request.inmuebleId, request.propietarioId, request.inquilinoId,
                        request.fechaInicio, request.fechaFin, request.montoBase
                );
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(contrato);
        } catch (InvalidIdException | InvalidFechasException | InvalidMontoException |
                 InmuebleNotFoundException | InmuebleNotDisponibleException |
                 PersonaNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> obtenerTodos() {
        List<Contrato> contratos = contratoService.obtenerTodos();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> obtenerPorId(@PathVariable Long id) {
        try {
            Optional<Contrato> contrato = contratoService.obtenerPorId(id);
            return contrato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/inmueble/{inmuebleId}")
    public ResponseEntity<List<Contrato>> obtenerPorInmueble(@PathVariable Long inmuebleId) {
        try {
            List<Contrato> contratos = contratoService.obtenerPorInmueble(inmuebleId);
            return ResponseEntity.ok(contratos);
        } catch (InmuebleNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

