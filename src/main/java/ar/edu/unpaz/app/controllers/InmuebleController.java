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

/**
 * Controlador REST para la entidad Inmueble.
 */
@RestController
@RequestMapping("/api/inmuebles")
public class InmuebleController {

    @Autowired
    private InmuebleService inmuebleService;

    /**
     * POST /api/inmuebles - Crear un nuevo Inmueble
     */
    @PostMapping
    public ResponseEntity<Inmueble> crearInmueble(@RequestBody Inmueble inmueble) {
        try {
            Inmueble nuevoInmueble = inmuebleService.crearInmueble(inmueble);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInmueble);
        } catch (InvalidInmuebleException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/inmuebles - Obtener todos los Inmuebles
     */
    @GetMapping
    public ResponseEntity<List<Inmueble>> obtenerTodos() {
        List<Inmueble> inmuebles = inmuebleService.obtenerTodos();
        return ResponseEntity.ok(inmuebles);
    }

    /**
     * GET /api/inmuebles/{id} - Obtener un Inmueble por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inmueble> obtenerPorId(@PathVariable Long id) {
        try {
            Optional<Inmueble> inmueble = inmuebleService.obtenerPorId(id);
            return inmueble.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/inmuebles/buscar/direccion?q=... - Buscar Inmuebles por dirección
     */
    @GetMapping("/buscar/direccion")
    public ResponseEntity<List<Inmueble>> buscarPorDireccion(@RequestParam String q) {
        try {
            List<Inmueble> inmuebles = inmuebleService.buscarPorDireccion(q);
            return ResponseEntity.ok(inmuebles);
        } catch (InvalidDireccionException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/inmuebles/buscar/tipo?tipo=... - Buscar Inmuebles por tipo
     */
    @GetMapping("/buscar/tipo")
    public ResponseEntity<List<Inmueble>> buscarPorTipo(@RequestParam String tipo) {
        try {
            List<Inmueble> inmuebles = inmuebleService.buscarPorTipo(tipo);
            return ResponseEntity.ok(inmuebles);
        } catch (InvalidTipoException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/inmuebles/{id} - Actualizar un Inmueble
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inmueble> actualizarInmueble(@PathVariable Long id, @RequestBody Inmueble inmuebleActualizado) {
        try {
            Inmueble inmueble = inmuebleService.actualizarInmueble(id, inmuebleActualizado);
            return ResponseEntity.ok(inmueble);
        } catch (InvalidIdException | InvalidInmuebleException | InmuebleNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DELETE /api/inmuebles/{id} - Eliminar un Inmueble
     */
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

