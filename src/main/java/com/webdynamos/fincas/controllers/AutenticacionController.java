package com.webdynamos.fincas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdynamos.fincas.dto.ArrendadorConPasswordDTO;
import com.webdynamos.fincas.services.JWTTokenService;

import java.util.Collections;

@RestController
@RequestMapping(value = "/jwt/security")
public class AutenticacionController {

    @Autowired
    JWTTokenService jwtTokenService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/autenticar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> autenticar(@RequestBody ArrendadorConPasswordDTO arrendadorConPasswordDTO) {
        System.out.println(arrendadorConPasswordDTO.getNombre());
        System.out.println(arrendadorConPasswordDTO.getApellido());

        // Verifica las credenciales aquí
        if (usuarioValido(arrendadorConPasswordDTO)) {
            String token = jwtTokenService.generarToken(arrendadorConPasswordDTO);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Credenciales incorrectas");
        }
    }

    private boolean usuarioValido(ArrendadorConPasswordDTO usuario) {
        // Implementa tu lógica de autenticación aquí
        // Esto es solo un ejemplo, deberías comparar con los datos reales de tu base de datos
        return "correctUsername".equals(usuario.getUsername()) && "correctPassword".equals(usuario.getPassword());
    }
}
