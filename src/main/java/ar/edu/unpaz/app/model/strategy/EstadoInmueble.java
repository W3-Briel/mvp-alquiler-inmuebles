package ar.edu.unpaz.app.model.strategy;

public interface EstadoInmueble {

    void alquilar(ar.edu.unpaz.app.model.Inmueble inmueble);

    void liberar(ar.edu.unpaz.app.model.Inmueble inmueble);

    void ponerEnMantenimiento(ar.edu.unpaz.app.model.Inmueble inmueble);

    String getNombreEstado();

}

