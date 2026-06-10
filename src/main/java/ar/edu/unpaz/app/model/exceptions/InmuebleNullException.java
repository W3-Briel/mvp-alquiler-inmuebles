package ar.edu.unpaz.app.model.exceptions;

/**
 * Excepción lanzada cuando se intenta operar con un Inmueble null.
 */
public class InmuebleNullException extends IllegalArgumentException {

    public InmuebleNullException(String message) {
        super(message);
    }

    public InmuebleNullException(String message, Throwable cause) {
        super(message, cause);
    }
}

