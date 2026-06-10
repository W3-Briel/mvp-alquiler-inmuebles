package ar.edu.unpaz.app.repositories;

import ar.edu.unpaz.app.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Persona.
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByDni(String dni);

    Optional<Persona> findByEmail(String email);

}

