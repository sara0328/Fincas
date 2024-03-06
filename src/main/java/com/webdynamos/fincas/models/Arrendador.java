package com.webdynamos.fincas.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity(name = "arrendador")
public class Arrendador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_arrendador;
    private int calificacion;
    private String nombre;
    private  String apellido;
    private String telefono;
    private String correo;

    @OneToMany(mappedBy = "arrendador")
    @JsonIgnore
    @ToString.Exclude
    private Set<Calificacion_arrendatario> calificacion_arrendatarios;

    @OneToMany(mappedBy = "arrendador")
    @JsonIgnore
    @ToString.Exclude
    private Set<Calificacion_propiedad> calificacion_propiedades;

    @OneToMany(mappedBy = "arrendador")
    @JsonIgnore
    @ToString.Exclude
    private Set<Solicitud> arrendador_solicitudes;

}
