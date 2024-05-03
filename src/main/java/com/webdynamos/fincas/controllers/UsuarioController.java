package com.webdynamos.fincas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdynamos.fincas.dto.UsuarioDTO;
import com.webdynamos.fincas.services.UsuarioService;

@RestController
@RequestMapping(value = "/jwt/security/usuario")
public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;


    @CrossOrigin
    @GetMapping(  produces = MediaType.APPLICATION_JSON_VALUE )
    public UsuarioDTO autenticar( Authentication authentication ) throws Exception{
        System.out.println( authentication );
        return usuarioService.autorizacion(authentication);
    }


    @CrossOrigin
    @GetMapping(  value = "/error", produces = MediaType.APPLICATION_JSON_VALUE )
    public UsuarioDTO error( Authentication authentication ) throws Exception{
        System.out.println( authentication );
        return usuarioService.autorizacion(authentication);
    }
}
