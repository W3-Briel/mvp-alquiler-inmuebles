package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporciona un DNI inválido.
 */
public class InvalidDniException extends IllegalArgumentException {

    public InvalidDniException(String message) {
        super(message);
    }

    public InvalidDniException(String message, Throwable cause) {
        super(message, cause);
    }
}

