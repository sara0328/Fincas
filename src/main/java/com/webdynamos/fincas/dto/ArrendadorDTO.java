package com.webdynamos.fincas.dto;

import com.webdynamos.fincas.models.Arrendador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Setter;
import lombok.Getter;

@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class ArrendadorDTO {

    private Long id_arrendador;
    private int calificacion;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
}
