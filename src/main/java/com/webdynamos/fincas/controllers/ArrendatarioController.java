package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.dto.ArrendatarioConPasswordDTO;
import com.webdynamos.fincas.dto.ArrendatarioDTO;
import com.webdynamos.fincas.dto.PropiedadDTO;
import com.webdynamos.fincas.services.ArrendatarioService;

import com.webdynamos.fincas.services.PropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> actualizarArrendatario(@RequestBody ArrendatarioDTO arrendatarioDTO) {
        ArrendatarioDTO updatedArrendatarioDTO = arrendatarioService.actualizarArrendatario(arrendatarioDTO);
        if (updatedArrendatarioDTO != null) {
            return new ResponseEntity<>(updatedArrendatarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ArrendatarioDTO> crearArrendatario(@RequestBody ArrendatarioConPasswordDTO arrendatarioConPasswordDTO) {
        ArrendatarioDTO createdArrendatarioDTO = arrendatarioService.crearArrendatario(arrendatarioConPasswordDTO);
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
    @Autowired
    private PropiedadService propiedadService;

    @GetMapping("/arrendatario/propiedades")
    public ResponseEntity<List<PropiedadDTO>> getPropiedades(@RequestParam Long arrendatarioId) {
        List<PropiedadDTO> propiedades = propiedadService.findByArrendatarioId(arrendatarioId);
        return ResponseEntity.ok(propiedades);
    }
}
