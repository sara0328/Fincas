package com.webdynamos.fincas.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.webdynamos.fincas.models.Propiedad;
import com.webdynamos.fincas.services.PropiedadService;
import java.util.List;


@RestController
@RequestMapping("/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @GetMapping
    public ResponseEntity<List<Propiedad>> getAllPropiedades() {
        List<Propiedad> propiedades = propiedadService.ListarPropiedades();
        return new ResponseEntity<>(propiedades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propiedad> getPropiedadById(@PathVariable("id") Long id) {
        Propiedad propiedad = propiedadService.obtenerPropiedadPorId(id);
        if (propiedad != null) {
            return new ResponseEntity<>(propiedad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Propiedad> createPropiedad(@RequestBody Propiedad propiedad) {
        Propiedad createdPropiedad = propiedadService.CrearPropiedad(propiedad);
        return new ResponseEntity<>(createdPropiedad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propiedad> updatePropiedad(@PathVariable("id") Long id, @RequestBody Propiedad propiedad) {
        Propiedad updatedPropiedad = propiedadService.actualizarPropiedad(id, propiedad);
        if (updatedPropiedad != null) {
            return new ResponseEntity<>(updatedPropiedad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePropiedad(@PathVariable("id") Long id) {
        boolean deleted = propiedadService.deletePropiedad(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}