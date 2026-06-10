package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporciona un Tipo de inmueble inválido.
 */
public class InvalidTipoException extends IllegalArgumentException {

    public InvalidTipoException(String message) {
        super(message);
    }

    public InvalidTipoException(String message, Throwable cause) {
        super(message, cause);
    }
}

