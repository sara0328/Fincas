package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.models.Calificacion_propiedad;
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
    public <CalificacionPropiedad> ResponseEntity<List<CalificacionPropiedad>> getAllCalificaciones() {
        @SuppressWarnings("unchecked")
        List<CalificacionPropiedad> calificaciones = (List<CalificacionPropiedad>) calificacionPropiedadService.getAllCalificaciones();
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calificacion_propiedad> getCalificacionById(@PathVariable("id") Long id) {
        Calificacion_propiedad calificacion = calificacionPropiedadService.getCalificacionById(id);
        return new ResponseEntity<>(calificacion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Calificacion_propiedad> createCalificacion(@RequestBody CalificacionPropiedadService calificacion) {
        Calificacion_propiedad createdCalificacion = calificacionPropiedadService.createCalificacion(calificacion);
        return new ResponseEntity<>(createdCalificacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calificacion_propiedad> updateCalificacion(@PathVariable("id") Long id, @RequestBody Calificacion_propiedad calificacion) {
        Calificacion_propiedad updatedCalificacion = calificacionPropiedadService.updateCalificacion(id, calificacion);
        return new ResponseEntity<>(updatedCalificacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable("id") Long id) {
        calificacionPropiedadService.deleteCalificacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}