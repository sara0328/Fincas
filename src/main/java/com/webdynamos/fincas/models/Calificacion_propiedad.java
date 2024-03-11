package com.webdynamos.fincas.models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@Entity( name = "arrendador_propiedad")
public class Calificacion_propiedad {

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

    public <CalificacionPropiedad> void setCalificacion(CalificacionPropiedad calificacion2) {
        this.calificacion = (int) calificacion2;
    }
}
