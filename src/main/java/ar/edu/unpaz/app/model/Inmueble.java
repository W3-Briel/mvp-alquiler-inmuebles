package ar.edu.unpaz.app.model;

import ar.edu.unpaz.app.model.strategy.EstadoDisponible;
import ar.edu.unpaz.app.model.strategy.EstadoInmueble;
import ar.edu.unpaz.app.model.strategy.EstadoInmuebleConverter;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Entidad que representa un Inmueble a alquilar.
 */
@Entity
@Table(name = "inmuebles")
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private BigDecimal precioTasado;

    @Convert(converter = EstadoInmuebleConverter.class)
    @Column(name = "estado", columnDefinition = "VARCHAR(50) default 'DISPONIBLE'")
    private EstadoInmueble estado;

    // Constructores
    public Inmueble() {
        this.estado = new EstadoDisponible();
    }

    public Inmueble(String direccion, String tipo, BigDecimal precioTasado) {
        this.direccion = direccion;
        this.tipo = tipo;
        this.precioTasado = precioTasado;
        this.estado = new EstadoDisponible();
    }

    // Métodos delegados del patrón Strategy
    public void alquilar() {
        if (estado != null) {
            estado.alquilar(this);
        }
    }

    public void liberar() {
        if (estado != null) {
            estado.liberar(this);
        }
    }

    public void ponerEnMantenimiento() {
        if (estado != null) {
            estado.ponerEnMantenimiento(this);
        }
    }

    public String getNombreEstado() {
        return estado != null ? estado.getNombreEstado() : "DESCONOCIDO";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecioTasado() {
        return precioTasado;
    }

    public void setPrecioTasado(BigDecimal precioTasado) {
        this.precioTasado = precioTasado;
    }

    public EstadoInmueble getEstado() {
        return estado;
    }

    public void setEstado(EstadoInmueble estado) {
        this.estado = estado;
    }
}
