package com.webdynamos.fincas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private List<String> roles;  // Lista de roles del usuario
}
