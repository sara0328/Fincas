package com.webdynamos.fincas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webdynamos.fincas.dto.ArrendadorConPasswordDTO;
import com.webdynamos.fincas.dto.UsuarioDTO;
import com.webdynamos.fincas.services.JWTTokenService;

@RestController
@RequestMapping(value = "/jwt/security/autenticar")
public class AutenticacionController {

    @Autowired
    JWTTokenService jwtTokenService;

    @CrossOrigin
    @PostMapping(  value = "/autenticar", produces = MediaType.APPLICATION_JSON_VALUE)
    public String autenticar( @RequestBody ArrendadorConPasswordDTO ArrendadorConPasswordDTO ){
        System.out.println( ArrendadorConPasswordDTO.getNombre() );
        System.out.println( ArrendadorConPasswordDTO.getApellido() );
        return jwtTokenService.generarToken(ArrendadorConPasswordDTO);
    }


    @CrossOrigin
    @PostMapping(  value = "/autenticar-correo-contrasena", produces = MediaType.APPLICATION_JSON_VALUE)
    public String autenticar( @RequestParam String correo, @RequestParam String contrasena ){
        ArrendadorConPasswordDTO arrendadorConPasswordDTO = new ArrendadorConPasswordDTO(null, 3, "Pablo", "Marquez", correo, null, contrasena, contrasena);
        return jwtTokenService.generarToken(arrendadorConPasswordDTO);
    }
}
