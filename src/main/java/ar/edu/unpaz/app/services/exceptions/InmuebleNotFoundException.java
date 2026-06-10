package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un Inmueble con el ID especificado.
 */
public class InmuebleNotFoundException extends IllegalArgumentException {

    public InmuebleNotFoundException(String message) {
        super(message);
    }

    public InmuebleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

