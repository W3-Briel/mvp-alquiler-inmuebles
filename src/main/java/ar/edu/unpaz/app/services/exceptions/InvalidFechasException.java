package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporcionan fechas inválidas en un contrato.
 */
public class InvalidFechasException extends IllegalArgumentException {

    public InvalidFechasException(String message) {
        super(message);
    }

    public InvalidFechasException(String message, Throwable cause) {
        super(message, cause);
    }
}

