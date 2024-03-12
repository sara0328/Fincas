package com.webdynamos.fincas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.services.ArrendatarioService;


@RestController
@RequestMapping("/arrendatarios")
public class ArrendatarioController {

    @Autowired
    private ArrendatarioService arrendatarioService;

    @GetMapping
    public ResponseEntity<?> ListarArrendatarios() {
        return new ResponseEntity<>(arrendatarioService.ListarArrendatarios(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerArrendatarioPorId(@PathVariable Long id) {
        Arrendatario arrendatario = arrendatarioService.obtenerArrendatarioPorId(id);
        if (arrendatario != null) {
            return new ResponseEntity<>(arrendatario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Arrendatario not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearArrendatario (@RequestBody Arrendatario arrendatario) {
        Arrendatario createdArrendatario = arrendatarioService.CrearArrendatario(arrendatario);
        return new ResponseEntity<>(createdArrendatario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArrendatario(@PathVariable Long id) {
        return new ResponseEntity<>("Delete arrendatario with ID: " + id, HttpStatus.OK);
    }
    
}