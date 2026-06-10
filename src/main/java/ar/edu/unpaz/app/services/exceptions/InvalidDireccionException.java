package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporciona una Dirección inválida.
 */
public class InvalidDireccionException extends IllegalArgumentException {

    public InvalidDireccionException(String message) {
        super(message);
    }

    public InvalidDireccionException(String message, Throwable cause) {
        super(message, cause);
    }
}

