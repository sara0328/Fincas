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
    public ResponseEntity<?> getAllArrendatarios() {
        // TODO: Implement logic to retrieve all arrendatarios
        return new ResponseEntity<>("Get all arrendatarios", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArrendatarioById(@PathVariable Long id) {
        Arrendatario arrendatario = arrendatarioService.getArrendatarioById(id);
        if (arrendatario != null) {
            return new ResponseEntity<>(arrendatario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Arrendatario not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createArrendatario(@RequestBody Arrendatario arrendatario) {
        // TODO: Implement logic to create a new arrendatario
        return new ResponseEntity<>("Create arrendatario", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArrendatario(@PathVariable Long id, @RequestBody Arrendatario arrendatario) {
        // TODO: Implement logic to update an existing arrendatario
        return new ResponseEntity<>("Update arrendatario with ID: " + id, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArrendatario(@PathVariable Long id) {
        // TODO: Implement logic to delete an arrendatario by ID
        return new ResponseEntity<>("Delete arrendatario with ID: " + id, HttpStatus.OK);
    }
}