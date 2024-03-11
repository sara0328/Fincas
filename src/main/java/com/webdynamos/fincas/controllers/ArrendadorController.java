package com.webdynamos.fincas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.services.ArrendadorService;




@RestController
@RequestMapping("/arrendadores")
public class ArrendadorController {

    @Autowired
    private ArrendadorService arrendadorService;

    @GetMapping("/{id}")
    public ResponseEntity<Arrendador> getArrendadorById(@PathVariable Long id) {
        Arrendador arrendador = arrendadorService.getArrendadorById(id);
        if (arrendador != null) {
            return new ResponseEntity<>(arrendador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Arrendador> createArrendador(@RequestBody Arrendador arrendador) {
        Arrendador createdArrendador = arrendadorService.CrearArrendador(arrendador);
        return new ResponseEntity<>(createdArrendador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arrendador> updateArrendador(@PathVariable Long id, @RequestBody Arrendador arrendador) {
        Arrendador updatedArrendador = arrendadorService.actualizarArrendador(id, arrendador);
        if (updatedArrendador != null) {
            return new ResponseEntity<>(updatedArrendador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArrendador(@PathVariable Long id) {
        boolean deleted = arrendadorService.deleteArrendador(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}