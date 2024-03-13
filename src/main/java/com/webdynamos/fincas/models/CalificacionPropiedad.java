package com.webdynamos.fincas.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity( name = "arrendador_propiedad")
public class CalificacionPropiedad {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_cal_propiedad;
    private int calificacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_arrendador", referencedColumnName = "id_arrendador")
    private Arrendador arrendador;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_propiedad", referencedColumnName = "id_propiedad")
    private Propiedad propiedad;

    public void setCalificacion(int calificacion2) {
        this.calificacion = calificacion2;
    }
}
