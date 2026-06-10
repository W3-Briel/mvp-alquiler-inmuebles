package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando no se encuentra una Cuota con el ID especificado.
 */
public class CuotaNotFoundException extends IllegalArgumentException {

    public CuotaNotFoundException(String message) {
        super(message);
    }

    public CuotaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

