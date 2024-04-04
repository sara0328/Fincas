package com.webdynamos.fincas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.services.ArrendatarioService;


@RestController
@RequestMapping("/arrendatarios")
public class ArrendatarioController {

    private ArrendatarioService arrendatarioService;

    public ArrendatarioController(ArrendatarioService arrendatarioService) {
        this.arrendatarioService = arrendatarioService;
    }

    @GetMapping
    public ResponseEntity<Object> ListarArrendatarios() {
        return new ResponseEntity<>(arrendatarioService.ListarArrendatarios(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerArrendatarioPorId(@PathVariable Long id) {
        Arrendatario arrendatario = arrendatarioService.obtenerArrendatarioPorId(id);
        if (arrendatario != null) {
            return new ResponseEntity<>(arrendatario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Arrendatario not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arrendatario> actualizarArrendatario(@PathVariable Long id, @RequestBody Arrendatario arrendatario) {
        Arrendatario updatedArrendatario = arrendatarioService.actualizarArrendatario(id, arrendatario);
        if (updatedArrendatario != null) {
            return new ResponseEntity<>(updatedArrendatario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> crearArrendatario (@RequestBody Arrendatario arrendatario) {
        Arrendatario createdArrendatario = arrendatarioService.CrearArrendatario(arrendatario);
        return new ResponseEntity<>(createdArrendatario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArrendatario(@PathVariable Long id) {
        boolean isDeleted = arrendatarioService.deleteArrendatario(id);
        if (isDeleted) {
            return new ResponseEntity<>("Arrendatario with ID: " + id + " was successfully deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Arrendatario not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }    
    
    
}