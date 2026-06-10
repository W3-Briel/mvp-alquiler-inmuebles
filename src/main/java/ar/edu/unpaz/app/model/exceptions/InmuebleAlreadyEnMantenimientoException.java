package ar.edu.unpaz.app.model.exceptions;

/**
 * Excepción lanzada cuando se intenta poner en mantenimiento un inmueble que ya está en mantenimiento
 * o cuando se intenta poner en mantenimiento un inmueble alquilado.
 */
public class InmuebleAlreadyEnMantenimientoException extends IllegalStateException {

    public InmuebleAlreadyEnMantenimientoException(String message) {
        super(message);
    }

    public InmuebleAlreadyEnMantenimientoException(String message, Throwable cause) {
        super(message, cause);
    }
}

