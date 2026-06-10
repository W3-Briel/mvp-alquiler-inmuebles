package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se intenta crear o actualizar una Persona con datos inválidos.
 */
public class InvalidPersonaException extends IllegalArgumentException {

    public InvalidPersonaException(String message) {
        super(message);
    }

    public InvalidPersonaException(String message, Throwable cause) {
        super(message, cause);
    }
}

