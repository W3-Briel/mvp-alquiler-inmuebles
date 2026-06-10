package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando no se encuentra una Persona con el ID especificado.
 */
public class PersonaNotFoundException extends IllegalArgumentException {

    public PersonaNotFoundException(String message) {
        super(message);
    }

    public PersonaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

