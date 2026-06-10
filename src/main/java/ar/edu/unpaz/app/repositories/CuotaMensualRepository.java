package ar.edu.unpaz.app.repositories;

import ar.edu.unpaz.app.model.Contrato;
import ar.edu.unpaz.app.model.CuotaMensual;
import ar.edu.unpaz.app.model.CuotaMensualEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad CuotaMensual.
 */
@Repository
public interface CuotaMensualRepository extends JpaRepository<CuotaMensual, Long> {

    List<CuotaMensual> findByContrato(Contrato contrato);

    List<CuotaMensual> findByEstado(CuotaMensualEstado estado);

    List<CuotaMensual> findByContratoAndEstado(Contrato contrato, CuotaMensualEstado estado);

}

