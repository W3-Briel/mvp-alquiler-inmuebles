package ar.edu.unpaz.app.controllers;

import java.time.LocalDate;

public class DeletionResponse {

    private String fechaEliminacion;
    private String mensaje;

    public DeletionResponse() {
    }

    public DeletionResponse(String mensaje) {
        this.fechaEliminacion = LocalDate.now().toString();
        this.mensaje = mensaje;
    }

    public String getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(String fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

