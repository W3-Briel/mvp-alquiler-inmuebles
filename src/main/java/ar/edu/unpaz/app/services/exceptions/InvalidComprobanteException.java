package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporciona un Número de Comprobante inválido.
 */
public class InvalidComprobanteException extends IllegalArgumentException {

    public InvalidComprobanteException(String message) {
        super(message);
    }

    public InvalidComprobanteException(String message, Throwable cause) {
        super(message, cause);
    }
}

