package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporciona un ID de cuota inválido.
 */
public class InvalidCuotaIdException extends IllegalArgumentException {

    public InvalidCuotaIdException(String message) {
        super(message);
    }

    public InvalidCuotaIdException(String message, Throwable cause) {
        super(message, cause);
    }
}

