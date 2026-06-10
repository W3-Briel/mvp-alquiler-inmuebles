package ar.edu.unpaz.app.repositories;

import ar.edu.unpaz.app.model.CuotaMensual;
import ar.edu.unpaz.app.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Pago.
 */
@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByCuota(CuotaMensual cuota);

}

