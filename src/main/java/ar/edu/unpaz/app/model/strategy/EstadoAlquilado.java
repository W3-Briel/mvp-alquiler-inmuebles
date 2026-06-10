package ar.edu.unpaz.app.model.strategy;

import ar.edu.unpaz.app.model.Inmueble;
import ar.edu.unpaz.app.model.exceptions.InmuebleAlreadyAlquiladoException;
import ar.edu.unpaz.app.model.exceptions.InmuebleAlreadyEnMantenimientoException;
import ar.edu.unpaz.app.model.exceptions.InmuebleNullException;

public class EstadoAlquilado implements EstadoInmueble {

    @Override
    public void alquilar(Inmueble inmueble) {
        throw new InmuebleAlreadyAlquiladoException("El inmueble ya está alquilado");
    }

    @Override
    public void liberar(Inmueble inmueble) {
        if (inmueble == null) throw new InmuebleNullException("Inmueble null");
        inmueble.setEstado(new EstadoDisponible());
    }

    @Override
    public void ponerEnMantenimiento(Inmueble inmueble) {
        throw new InmuebleAlreadyEnMantenimientoException("No se puede poner en mantenimiento un inmueble alquilado");
    }

    @Override
    public String getNombreEstado() {
        return "ALQUILADO";
    }
}

