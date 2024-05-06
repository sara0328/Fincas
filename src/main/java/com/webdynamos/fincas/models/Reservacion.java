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

    // Getters and setters
}
