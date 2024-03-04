package com.webdynamos.fincas.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity( name = "arrendador_propiedad")
public class Arrendador_propiedad {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id_cal_propiedad;
    private int calificacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_arrendador", referencedColumnName = "id_arrendador")
    private Arrendador arrendador;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_propiedad", referencedColumnName = "id_propiedad")
    private Propiedad propiedad;
}
