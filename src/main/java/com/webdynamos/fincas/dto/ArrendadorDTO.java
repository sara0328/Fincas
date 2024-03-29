package com.webdynamos.fincas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ArrendadorDTO {

    private Long id_arrendador;
    private int calificacion;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
}
