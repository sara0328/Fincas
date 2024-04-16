package com.webdynamos.fincas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webdynamos.fincas.models.CalificacionArrendatario;
import com.webdynamos.fincas.services.CalificacionArrendatarioService;

import java.util.Optional;


@RestController
@RequestMapping("/calificacionesArrendatario")
public class CalificacionArrendatarioController {

    @Autowired
    private CalificacionArrendatarioService calificacionArrendatarioService;

    @PostMapping("/crear")
    public ResponseEntity<CalificacionArrendatario> addCalificacionArrendatario(@RequestBody CalificacionArrendatario calificacionArrendatario) {
        CalificacionArrendatario nuevaCalificacion = calificacionArrendatarioService.CrearArrendador_arrendatario(calificacionArrendatario);
        if (nuevaCalificacion != null) {
            return new ResponseEntity<>(nuevaCalificacion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<CalificacionArrendatario> getCalificacionArrendatario(@PathVariable("id") Long id) {
        System.out.println("Fetching CalificacionArrendatario with ID: " + id); // Simple console log
        Optional<CalificacionArrendatario> calificacionArrendatario = calificacionArrendatarioService.getCalificacionArrendatario(id);
            if (calificacionArrendatario.isPresent()) {
                return new ResponseEntity<>(calificacionArrendatario.get(), HttpStatus.OK);
        } else {
            System.out.println("CalificacionArrendatario not found for ID: " + id); // Log when not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<CalificacionArrendatario> updateCalificacionArrendatario(@PathVariable("id") Long id, @RequestBody CalificacionArrendatario calificacionArrendatario) {
        Optional<CalificacionArrendatario> updatedCalificacionArrendatario = calificacionArrendatarioService.updateCalificacionArrendatario(id, calificacionArrendatario);
        if (updatedCalificacionArrendatario.isPresent()) {
            return new ResponseEntity<CalificacionArrendatario>(updatedCalificacionArrendatario.get(), HttpStatus.OK);
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