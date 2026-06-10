package ar.edu.unpaz.app.model.strategy;
import ar.edu.unpaz.app.model.*;

public interface EstadoInmueble {

    void alquilar(Inmueble inmueble);

    void liberar(Inmueble inmueble);

    void ponerEnMantenimiento(Inmueble inmueble);

    String getNombreEstado();

}

