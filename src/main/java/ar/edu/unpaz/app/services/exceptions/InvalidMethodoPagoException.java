package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporciona un Método de Pago inválido.
 */
public class InvalidMethodoPagoException extends IllegalArgumentException {

    public InvalidMethodoPagoException(String message) {
        super(message);
    }

    public InvalidMethodoPagoException(String message, Throwable cause) {
        super(message, cause);
    }
}

