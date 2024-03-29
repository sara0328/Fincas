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
@AllArgsConstructor
@Entity(name = "arrendatario")
public class Arrendatario {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_arrendatario;
    private String nombre;
    private  String apellido;
    private String telefono;
    private String correo;

    @OneToMany(mappedBy = "arrendatario")
    @JsonIgnore
    @ToString.Exclude
    Set<CalificacionArrendatario> calificacion_calificacion_arrendatarios;

    @OneToMany(mappedBy = "arrendatario")
    @JsonIgnore
    @ToString.Exclude
    Set<Solicitud> arrendatario_solicitudes;


    @OneToMany(mappedBy = "arrendatario")
    @JsonIgnore
    private Set<Propiedad> arrendatario_propiedades;


}
