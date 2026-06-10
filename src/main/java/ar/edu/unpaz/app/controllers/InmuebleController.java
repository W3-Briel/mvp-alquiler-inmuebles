package ar.edu.unpaz.app.controllers;

import ar.edu.unpaz.app.model.Inmueble;
import ar.edu.unpaz.app.services.InmuebleService;
import ar.edu.unpaz.app.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inmuebles")
public class InmuebleController {

    @Autowired
    private InmuebleService inmuebleService;

    @PostMapping
    public ResponseEntity<Inmueble> crearInmueble(@RequestBody Inmueble inmueble) {
        try {
            Inmueble nuevoInmueble = inmuebleService.crearInmueble(inmueble);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInmueble);
        } catch (InvalidInmuebleException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Inmueble>> obtenerTodos() {
        List<Inmueble> inmuebles = inmuebleService.obtenerTodos();
        return ResponseEntity.ok(inmuebles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inmueble> obtenerPorId(@PathVariable Long id) {
        try {
            Optional<Inmueble> inmueble = inmuebleService.obtenerPorId(id);
            return inmueble.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar/direccion")
    public ResponseEntity<List<Inmueble>> buscarPorDireccion(@RequestParam String q) {
        try {
            List<Inmueble> inmuebles = inmuebleService.buscarPorDireccion(q);
            return ResponseEntity.ok(inmuebles);
        } catch (InvalidDireccionException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/buscar/tipo")
    public ResponseEntity<List<Inmueble>> buscarPorTipo(@RequestParam String tipo) {
        try {
            List<Inmueble> inmuebles = inmuebleService.buscarPorTipo(tipo);
            return ResponseEntity.ok(inmuebles);
        } catch (InvalidTipoException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inmueble> actualizarInmueble(@PathVariable Long id, @RequestBody Inmueble inmuebleActualizado) {
        try {
            Inmueble inmueble = inmuebleService.actualizarInmueble(id, inmuebleActualizado);
            return ResponseEntity.ok(inmueble);
        } catch (InvalidIdException | InvalidInmuebleException | InmuebleNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletionResponse> eliminarInmueble(@PathVariable Long id) {
        try {
            inmuebleService.eliminarInmueble(id);
            DeletionResponse resp = new DeletionResponse("Eliminación realizada correctamente");
            return ResponseEntity.ok(resp);
        } catch (InvalidIdException | InmuebleNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

