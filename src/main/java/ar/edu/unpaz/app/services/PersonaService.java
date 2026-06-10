package ar.edu.unpaz.app.services;

import ar.edu.unpaz.app.model.Persona;
import ar.edu.unpaz.app.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar Personas (CRUD).
 */
@Service
@Transactional
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona crearPersona(Persona persona) {
        if (persona == null) {
            throw new IllegalArgumentException("Persona no puede ser null");
        }
        return personaRepository.save(persona);
    }

    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return personaRepository.findById(id);
    }

    public Optional<Persona> obtenerPorDni(String dni) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("DNI no puede ser null o vacío");
        }
        return personaRepository.findByDni(dni);
    }

    public Optional<Persona> obtenerPorEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email no puede ser null o vacío");
        }
        return personaRepository.findByEmail(email);
    }

    public Persona actualizarPersona(Long id, Persona personaActualizada) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        if (personaActualizada == null) {
            throw new IllegalArgumentException("Persona actualizada no puede ser null");
        }
        return personaRepository.findById(id)
                .map(persona -> {
                    persona.setNombreCompleto(personaActualizada.getNombreCompleto());
                    persona.setEmail(personaActualizada.getEmail());
                    persona.setTelefono(personaActualizada.getTelefono());
                    return personaRepository.save(persona);
                })
                .orElseThrow(() -> new IllegalArgumentException("Persona con ID " + id + " no encontrada"));
    }

    public void eliminarPersona(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        if (!personaRepository.existsById(id)) {
            throw new IllegalArgumentException("Persona con ID " + id + " no encontrada");
        }
        personaRepository.deleteById(id);
    }
}

