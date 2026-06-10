package ar.edu.unpaz.app.model.exceptions;

/**
 * Excepción lanzada cuando se intenta alquilar un inmueble que ya está alquilado.
 */
public class InmuebleAlreadyAlquiladoException extends IllegalStateException {

    public InmuebleAlreadyAlquiladoException(String message) {
        super(message);
    }

    public InmuebleAlreadyAlquiladoException(String message, Throwable cause) {
        super(message, cause);
    }
}

