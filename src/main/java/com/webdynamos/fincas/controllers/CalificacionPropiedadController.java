package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.models.CalificacionPropiedad;
import com.webdynamos.fincas.services.CalificacionPropiedadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 // Import the missing class



@RestController
@RequestMapping("/calificacionesPropiedad")
public class CalificacionPropiedadController {

    private final CalificacionPropiedadService calificacionPropiedadService;

    public CalificacionPropiedadController(CalificacionPropiedadService calificacionPropiedadService) {
        this.calificacionPropiedadService = calificacionPropiedadService;
    }

    @GetMapping
    public ResponseEntity<List<CalificacionPropiedad>> getAllCalificaciones() {
        List<CalificacionPropiedad> calificaciones = calificacionPropiedadService.getAllCalificaciones();
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionPropiedad> getCalificacionById(@PathVariable("id") Long id) {
        CalificacionPropiedad calificacion = calificacionPropiedadService.getCalificacionById(id);
        return new ResponseEntity<>(calificacion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CalificacionPropiedad> createCalificacion(@RequestBody CalificacionPropiedad calificacion) {
        CalificacionPropiedad createdCalificacion = calificacionPropiedadService.createCalificacion(calificacion);
        return new ResponseEntity<>(createdCalificacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionPropiedad> updateCalificacion(@PathVariable("id") Long id, @RequestBody CalificacionPropiedad calificacion) {
        CalificacionPropiedad updatedCalificacion = calificacionPropiedadService.updateCalificacion(id, calificacion);
        return new ResponseEntity<>(updatedCalificacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable("id") Long id) {
        calificacionPropiedadService.deleteCalificacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}