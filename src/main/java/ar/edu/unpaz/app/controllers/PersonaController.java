package ar.edu.unpaz.app.controllers;

import ar.edu.unpaz.app.model.Persona;
import ar.edu.unpaz.app.services.PersonaService;
import ar.edu.unpaz.app.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para la entidad Persona.
 */
@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    /**
     * POST /api/personas - Crear una nueva Persona
     */
    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        try {
            Persona nuevaPersona = personaService.crearPersona(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPersona);
        } catch (InvalidPersonaException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/personas - Obtener todas las Personas
     */
    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodas() {
        List<Persona> personas = personaService.obtenerTodas();
        return ResponseEntity.ok(personas);
    }

    /**
     * GET /api/personas/{id} - Obtener una Persona por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable Long id) {
        try {
            Optional<Persona> persona = personaService.obtenerPorId(id);
            return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/personas/dni/{dni} - Obtener una Persona por DNI
     */
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Persona> obtenerPorDni(@PathVariable String dni) {
        try {
            Optional<Persona> persona = personaService.obtenerPorDni(dni);
            return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (InvalidDniException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /api/personas/email/{email} - Obtener una Persona por Email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Persona> obtenerPorEmail(@PathVariable String email) {
        try {
            Optional<Persona> persona = personaService.obtenerPorEmail(email);
            return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (InvalidEmailException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * PUT /api/personas/{id} - Actualizar una Persona
     */
    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaActualizada) {
        try {
            Persona persona = personaService.actualizarPersona(id, personaActualizada);
            return ResponseEntity.ok(persona);
        } catch (InvalidIdException | InvalidPersonaException | PersonaNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * DELETE /api/personas/{id} - Eliminar una Persona
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DeletionResponse> eliminarPersona(@PathVariable Long id) {
        try {
            personaService.eliminarPersona(id);
            DeletionResponse resp = new DeletionResponse("Eliminación realizada correctamente");
            return ResponseEntity.ok(resp);
        } catch (InvalidIdException | PersonaNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

