package com.webdynamos.fincas.dto;

import java.util.Date;

public class ArrendatarioDTO {
    private Long id; // Optional based on whether you want to expose the ID
    private String nombre;
    private String direccion;
    private Date fechaInicioContrato;
    private Date fechaFinContrato;

    // Constructor por default
    public ArrendatarioDTO() {
    }


    public ArrendatarioDTO(Long id, String nombre, String direccion, Date fechaInicioContrato, Date fechaFinContrato) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaInicioContrato = fechaInicioContrato;
        this.fechaFinContrato = fechaFinContrato;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "ArrendatarioDTO [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion +
                ", fechaInicioContrato=" + fechaInicioContrato + ", fechaFinContrato=" + fechaFinContrato + "]";
    }
}
