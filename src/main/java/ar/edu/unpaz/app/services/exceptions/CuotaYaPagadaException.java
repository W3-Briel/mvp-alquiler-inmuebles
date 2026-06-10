package ar.edu.unpaz.app.services.exceptions;

/**
 * Excepción lanzada cuando se intenta realizar un pago a una cuota que ya está completamente pagada.
 */
public class CuotaYaPagadaException extends IllegalStateException {

    public CuotaYaPagadaException(String message) {
        super(message);
    }

    public CuotaYaPagadaException(String message, Throwable cause) {
        super(message, cause);
    }
}

