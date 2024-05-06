package com.webdynamos.fincas.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservaciones")
public class Reservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPropiedad")
    private Propiedad propiedad;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private LocalDateTime fechaReserva;
    private LocalDateTime fechaExpiracion;
    private String estado;
    private Double deposito;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Propiedad getPropiedad() {
        return propiedad;
    }
    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }
    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }
    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Double getDeposito() {
        return deposito;
    }
    public void setDeposito(Double deposito) {
        this.deposito = deposito;
    }

    // Getters and setters
    
}
