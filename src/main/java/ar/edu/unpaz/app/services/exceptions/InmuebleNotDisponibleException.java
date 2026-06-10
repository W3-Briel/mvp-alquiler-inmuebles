package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se intenta alquilar un inmueble que no está disponible.
 */
public class InmuebleNotDisponibleException extends IllegalStateException {

    public InmuebleNotDisponibleException(String message) {
        super(message);
    }

    public InmuebleNotDisponibleException(String message, Throwable cause) {
        super(message, cause);
    }
}

