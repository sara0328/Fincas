package com.webdynamos.fincas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webdynamos.fincas.dto.ArrendatarioDTO;
import com.webdynamos.fincas.services.ArrendatarioService;

@RestController
@RequestMapping("/arrendatarios")
public class ArrendatarioController {

    private final ArrendatarioService arrendatarioService;

    public ArrendatarioController(ArrendatarioService arrendatarioService) {
        this.arrendatarioService = arrendatarioService;
    }

    @GetMapping
    public ResponseEntity<List<ArrendatarioDTO>> listarArrendatarios() {
        List<ArrendatarioDTO> arrendatarios = arrendatarioService.listarArrendatarios();
        return new ResponseEntity<>(arrendatarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerArrendatarioPorId(@PathVariable Long id) {
        ArrendatarioDTO arrendatarioDTO = arrendatarioService.obtenerArrendatarioPorId(id);
        if (arrendatarioDTO != null) {
            return new ResponseEntity<>(arrendatarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Arrendatario not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarArrendatario(@PathVariable Long id, @RequestBody ArrendatarioDTO arrendatarioDTO) {
        ArrendatarioDTO updatedArrendatarioDTO = arrendatarioService.actualizarArrendatario(id, arrendatarioDTO);
        if (updatedArrendatarioDTO != null) {
            return new ResponseEntity<>(updatedArrendatarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ArrendatarioDTO> crearArrendatario(@RequestBody ArrendatarioDTO arrendatarioDTO) {
        ArrendatarioDTO createdArrendatarioDTO = arrendatarioService.crearArrendatario(arrendatarioDTO);
        return new ResponseEntity<>(createdArrendatarioDTO, HttpStatus.CREATED);
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
