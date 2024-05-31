package com.webdynamos.fincas.dto;


import com.webdynamos.fincas.enums.ROLE;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArrendadorDTO {

    private Long id_arrendador;
    private int calificacion;
    private String username;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private ROLE role;
}
