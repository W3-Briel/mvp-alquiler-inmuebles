package ar.edu.unpaz.app.model.strategy;

import ar.edu.unpaz.app.model.Inmueble;
import ar.edu.unpaz.app.model.exceptions.InmuebleAlreadyDisponibleException;
import ar.edu.unpaz.app.model.exceptions.InmuebleNullException;

/**
 * Estado: Disponible
 */
public class EstadoDisponible implements EstadoInmueble {

    @Override
    public void alquilar(Inmueble inmueble) {
        if (inmueble == null) throw new InmuebleNullException("Inmueble null");
        // transición válida: Disponible -> Alquilado
        inmueble.setEstado(new EstadoAlquilado());
    }

    @Override
    public void liberar(Inmueble inmueble) {
        // liberar desde Disponible no tiene sentido
        throw new InmuebleAlreadyDisponibleException("No se puede liberar un inmueble que ya está disponible");
    }

    @Override
    public void ponerEnMantenimiento(Inmueble inmueble) {
        if (inmueble == null) throw new InmuebleNullException("Inmueble null");
        inmueble.setEstado(new EstadoEnMantenimiento());
    }

    @Override
    public String getNombreEstado() {
        return "DISPONIBLE";
    }
}

