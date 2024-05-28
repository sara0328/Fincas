package com.webdynamos.fincas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropiedadDTO {
    private Long id_propiedad;
    private int calificacion;
    private float precio;
    private String ubicacion;
    private int disponibilidad;
    private String descripcion;
    private String imagenUrl;
}
