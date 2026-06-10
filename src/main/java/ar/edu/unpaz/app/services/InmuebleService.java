package ar.edu.unpaz.app.services;

import ar.edu.unpaz.app.model.Inmueble;
import ar.edu.unpaz.app.repositories.InmuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar Inmuebles (CRUD y búsquedas).
 */
@Service
@Transactional
public class InmuebleService {

    @Autowired
    private InmuebleRepository inmuebleRepository;

    public Inmueble crearInmueble(Inmueble inmueble) {
        if (inmueble == null) {
            throw new IllegalArgumentException("Inmueble no puede ser null");
        }
        return inmuebleRepository.save(inmueble);
    }

    public List<Inmueble> obtenerTodos() {
        return inmuebleRepository.findAll();
    }

    public Optional<Inmueble> obtenerPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return inmuebleRepository.findById(id);
    }

    public List<Inmueble> buscarPorDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("Dirección no puede ser null o vacía");
        }
        return inmuebleRepository.findByDireccionContainsIgnoreCase(direccion);
    }

    public List<Inmueble> buscarPorTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo no puede ser null o vacío");
        }
        return inmuebleRepository.findByTipo(tipo);
    }

    public Inmueble actualizarInmueble(Long id, Inmueble inmuebleActualizado) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        if (inmuebleActualizado == null) {
            throw new IllegalArgumentException("Inmueble actualizado no puede ser null");
        }
        return inmuebleRepository.findById(id)
                .map(inmueble -> {
                    inmueble.setDireccion(inmuebleActualizado.getDireccion());
                    inmueble.setTipo(inmuebleActualizado.getTipo());
                    inmueble.setPrecioTasado(inmuebleActualizado.getPrecioTasado());
                    return inmuebleRepository.save(inmueble);
                })
                .orElseThrow(() -> new IllegalArgumentException("Inmueble con ID " + id + " no encontrado"));
    }

    public void eliminarInmueble(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        if (!inmuebleRepository.existsById(id)) {
            throw new IllegalArgumentException("Inmueble con ID " + id + " no encontrado");
        }
        inmuebleRepository.deleteById(id);
    }
}

