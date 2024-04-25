package com.webdynamos.fincas.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArrendatarioDTO {

    private Long id_arrendatario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

}