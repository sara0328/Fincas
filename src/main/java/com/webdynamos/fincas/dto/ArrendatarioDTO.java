package com.webdynamos.fincas.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArrendatarioDTO {

    private Long id_arrendatario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

}