package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se intenta crear o actualizar un Inmueble con datos inválidos.
 */
public class InvalidInmuebleException extends IllegalArgumentException {

    public InvalidInmuebleException(String message) {
        super(message);
    }

    public InvalidInmuebleException(String message, Throwable cause) {
        super(message, cause);
    }
}

