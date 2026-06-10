package ar.edu.unpaz.app.model.strategy;

import ar.edu.unpaz.app.model.Inmueble;
import ar.edu.unpaz.app.model.exceptions.InmuebleAlreadyEnMantenimientoException;
import ar.edu.unpaz.app.model.exceptions.InmuebleEnMantenimientoException;
import ar.edu.unpaz.app.model.exceptions.InmuebleNullException;

/**
 * Estado: En mantenimiento
 */
public class EstadoEnMantenimiento implements EstadoInmueble {

    @Override
    public void alquilar(Inmueble inmueble) {
        throw new InmuebleEnMantenimientoException("No se puede alquilar un inmueble en mantenimiento");
    }

    @Override
    public void liberar(Inmueble inmueble) {
        if (inmueble == null) throw new InmuebleNullException("Inmueble null");
        // Al liberar desde mantenimiento -> disponible
        inmueble.setEstado(new EstadoDisponible());
    }

    @Override
    public void ponerEnMantenimiento(Inmueble inmueble) {
        throw new InmuebleAlreadyEnMantenimientoException("El inmueble ya está en mantenimiento");
    }

    @Override
    public String getNombreEstado() {
        return "EN_MANTENIMIENTO";
    }
}

