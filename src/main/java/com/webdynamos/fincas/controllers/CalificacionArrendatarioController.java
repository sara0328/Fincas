package com.webdynamos.fincas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webdynamos.fincas.models.Calificacion_arrendatario;
import com.webdynamos.fincas.services.CalificacionArrendatarioService;

import java.util.Optional;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionArrendatarioController {

    @Autowired
    private CalificacionArrendatarioService calificacionArrendatarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Calificacion_arrendatario> getCalificacionArrendatario(@PathVariable("id") Long id) {
        Optional<Calificacion_arrendatario> calificacionArrendatario = calificacionArrendatarioService.getCalificacionArrendatario(id);
        if (calificacionArrendatario.isPresent()) {
            return new ResponseEntity<>(calificacionArrendatario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calificacion_arrendatario> updateCalificacionArrendatario(@PathVariable("id") Long id, @RequestBody Calificacion_arrendatario calificacionArrendatario) {
        Optional<Calificacion_arrendatario> updatedCalificacionArrendatario = calificacionArrendatarioService.updateCalificacionArrendatario(id, calificacionArrendatario);
        if (updatedCalificacionArrendatario != null) {
            return new ResponseEntity<Calificacion_arrendatario>(updatedCalificacionArrendatario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacionArrendatario(@PathVariable("id") Long id) {
        boolean deleted = calificacionArrendatarioService.deleteCalificacionArrendatario(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}