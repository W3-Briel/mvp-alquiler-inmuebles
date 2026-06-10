package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporciona un Email inválido.
 */
public class InvalidEmailException extends IllegalArgumentException {

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}

