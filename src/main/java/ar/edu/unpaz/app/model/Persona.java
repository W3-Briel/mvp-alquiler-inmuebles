package ar.edu.unpaz.app.model;

import jakarta.persistence.*;

/**
 * Entidad que representa una Persona (propietario, inquilino, garante).
 */
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreCompleto;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;

    // Constructores
    public Persona() {
    }

    public Persona(String nombreCompleto, String dni, String email, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
