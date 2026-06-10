package ar.edu.unpaz.app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Entidad que representa un Contrato de alquiler.
 */
@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private BigDecimal montoBase;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inmueble_id", nullable = false)
    private Inmueble inmueble;

    @ManyToOne(optional = false)
    @JoinColumn(name = "propietario_id", nullable = false)
    private Persona propietario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inquilino_id", nullable = false)
    private Persona inquilino;

    @ManyToOne
    @JoinColumn(name = "garante_id", nullable = true)
    private Persona garante;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuotaMensual> cuotas = new ArrayList<>();

    // Constructores
    public Contrato() {
    }

    public Contrato(LocalDate fechaInicio, LocalDate fechaFin, BigDecimal montoBase,
                    Inmueble inmueble, Persona propietario, Persona inquilino) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.montoBase = montoBase;
        this.inmueble = inmueble;
        this.propietario = propietario;
        this.inquilino = inquilino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(BigDecimal montoBase) {
        this.montoBase = montoBase;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    public Persona getInquilino() {
        return inquilino;
    }

    public void setInquilino(Persona inquilino) {
        this.inquilino = inquilino;
    }

    public Persona getGarante() {
        return garante;
    }

    public void setGarante(Persona garante) {
        this.garante = garante;
    }

    public List<CuotaMensual> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<CuotaMensual> cuotas) {
        this.cuotas = cuotas;
    }
}
