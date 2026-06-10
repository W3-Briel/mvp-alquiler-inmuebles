package ar.edu.unpaz.app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal montoAbonado;

    @Column(nullable = false)
    private LocalDate fechaPago;

    @Column(nullable = false)
    private String metodoPago;

    @Column(nullable = false)
    private String nroComprobante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cuota_id", nullable = false)
    private CuotaMensual cuota;

    public Pago() {
    }

    public Pago(BigDecimal montoAbonado, LocalDate fechaPago, String metodoPago,
                String nroComprobante, CuotaMensual cuota) {
        this.montoAbonado = montoAbonado;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.nroComprobante = nroComprobante;
        this.cuota = cuota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(BigDecimal montoAbonado) {
        this.montoAbonado = montoAbonado;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public CuotaMensual getCuota() {
        return cuota;
    }

    public void setCuota(CuotaMensual cuota) {
        this.cuota = cuota;
    }
}
