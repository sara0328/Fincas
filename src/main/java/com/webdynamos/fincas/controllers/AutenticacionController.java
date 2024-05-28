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
import com.webdynamos.fincas.models.Arrendador; // Asegúrate de importar la clase Arrendador
import com.webdynamos.fincas.repository.ArrendadorRepository;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping(value = "/jwt/security")
public class AutenticacionController {

    @Autowired
    JWTTokenService jwtTokenService;

    @Autowired
    ArrendadorRepository arrendadorRepository;

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

    private boolean usuarioValido(ArrendadorConPasswordDTO usuarioDTO) {
        Optional<Arrendador> arrendadorOpt = arrendadorRepository.findByUsername(usuarioDTO.getUsername());
        if (arrendadorOpt.isPresent()) {
            Arrendador arrendador = arrendadorOpt.get();
            return arrendador.getPassword().equals(usuarioDTO.getPassword());
        }
        return false;
    }
}
