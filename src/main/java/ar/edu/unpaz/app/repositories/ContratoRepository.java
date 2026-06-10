package ar.edu.unpaz.app.repositories;

import ar.edu.unpaz.app.model.Contrato;
import ar.edu.unpaz.app.model.Inmueble;
import ar.edu.unpaz.app.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Contrato.
 */
@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findByInmueble(Inmueble inmueble);

    List<Contrato> findByInquilino(Persona inquilino);

    List<Contrato> findByPropietario(Persona propietario);

}

