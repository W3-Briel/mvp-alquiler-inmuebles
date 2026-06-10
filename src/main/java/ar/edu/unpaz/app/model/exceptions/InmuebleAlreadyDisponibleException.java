package ar.edu.unpaz.app.model.exceptions;

/**
 * Excepción lanzada cuando se intenta liberar un inmueble que ya está disponible.
 */
public class InmuebleAlreadyDisponibleException extends IllegalStateException {

    public InmuebleAlreadyDisponibleException(String message) {
        super(message);
    }

    public InmuebleAlreadyDisponibleException(String message, Throwable cause) {
        super(message, cause);
    }
}

