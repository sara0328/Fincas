package com.webdynamos.fincas.models;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity(name = "arrendador_arrendatario")
public class Calificacion_arrendatario {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id_cal_arrendador;
    private int calificacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_arrendador", referencedColumnName = "id_arrendador")
    private Arrendador arrendador;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_arrendatario", referencedColumnName = "id_arrendatario")
    private Arrendatario arrendatario;

}
