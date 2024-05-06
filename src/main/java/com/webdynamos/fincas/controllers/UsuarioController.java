package com.webdynamos.fincas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.webdynamos.fincas.dto.UsuarioDTO;
import com.webdynamos.fincas.services.UsuarioService;

@RestController
@RequestMapping(value = "/jwt/security/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO autenticar(Authentication authentication) throws Exception {
        return usuarioService.autorizacion(authentication);
    }

    @CrossOrigin
    @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public String error() {
        return "Error handling path"; // Cambia esto según la lógica de error que desees implementar
    }
}
