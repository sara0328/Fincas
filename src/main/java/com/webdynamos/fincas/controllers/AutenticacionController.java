package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.dto.ArrendadorConPasswordDTO;
import com.webdynamos.fincas.enums.ROLE;
import com.webdynamos.fincas.services.ArrendadorService;
import com.webdynamos.fincas.services.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/jwt/security")
public class AutenticacionController {

    @Autowired
    private JWTTokenService jwtTokenService;

    @Autowired
    private ArrendadorService arrendadorService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/autenticar")
    public ResponseEntity<?> autenticar(@RequestBody ArrendadorConPasswordDTO arrendadorConPasswordDTO) {
        System.out.println(arrendadorConPasswordDTO.getNombre());
        System.out.println(arrendadorConPasswordDTO.getApellido());

        if (arrendadorService.usuarioValido(arrendadorConPasswordDTO)) {
            ROLE role = arrendadorService.getUserRole(arrendadorConPasswordDTO.getUsername());
            arrendadorConPasswordDTO.setRole(role);
            String token = jwtTokenService.generarToken(arrendadorConPasswordDTO);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Credenciales incorrectas");
        }
    }
}
