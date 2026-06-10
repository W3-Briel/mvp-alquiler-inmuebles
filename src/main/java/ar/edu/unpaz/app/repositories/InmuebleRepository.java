package ar.edu.unpaz.app.repositories;

import ar.edu.unpaz.app.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Inmueble.
 */
@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {

    List<Inmueble> findByDireccionContainsIgnoreCase(String direccion);

    List<Inmueble> findByTipo(String tipo);

}

