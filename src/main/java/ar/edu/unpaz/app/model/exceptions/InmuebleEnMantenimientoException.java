package ar.edu.unpaz.app.model.exceptions;

/**
 * Excepción lanzada cuando se intenta alquilar un inmueble que está en mantenimiento.
 */
public class InmuebleEnMantenimientoException extends IllegalStateException {

    public InmuebleEnMantenimientoException(String message) {
        super(message);
    }

    public InmuebleEnMantenimientoException(String message, Throwable cause) {
        super(message, cause);
    }
}

