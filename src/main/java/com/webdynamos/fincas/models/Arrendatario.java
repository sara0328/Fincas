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
    private String username;
    private String nombre;
    private  String apellido;
    private String telefono;
    private String correo;
    private String password;

    @OneToMany(mappedBy = "arrendatario")
    @JsonIgnore
    @ToString.Exclude
    private Set<CalificacionArrendatario> calificacion_calificacion_arrendatarios;

    @OneToMany(mappedBy = "arrendatario")
    @JsonIgnore
    @ToString.Exclude
    private Set<Solicitud> arrendatario_solicitudes;


    @OneToMany(mappedBy = "arrendatario")
    @JsonIgnore
    private Set<Propiedad> arrendatario_propiedades;


    public void setId(Long id) {
        this.id_arrendatario = id;
    }


}
