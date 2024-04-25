package com.webdynamos.fincas.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalificacionPropiedadDTO {
    private int calificacion;
    private Long id_arrendatario;
}
