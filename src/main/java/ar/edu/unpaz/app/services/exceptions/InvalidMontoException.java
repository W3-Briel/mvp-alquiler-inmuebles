package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se proporciona un Monto inválido (negativo, cero, nulo).
 */
public class InvalidMontoException extends IllegalArgumentException {

    public InvalidMontoException(String message) {
        super(message);
    }

    public InvalidMontoException(String message, Throwable cause) {
        super(message, cause);
    }
}

