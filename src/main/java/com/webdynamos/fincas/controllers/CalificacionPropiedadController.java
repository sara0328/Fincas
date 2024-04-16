package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.dto.CalificacionPropiedadDTO;
import com.webdynamos.fincas.models.CalificacionPropiedad;
import com.webdynamos.fincas.services.CalificacionPropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificacionesPropiedad")
public class CalificacionPropiedadController {

    private final CalificacionPropiedadService calificacionPropiedadService;

    @Autowired
    public CalificacionPropiedadController(CalificacionPropiedadService calificacionPropiedadService) {
        this.calificacionPropiedadService = calificacionPropiedadService;
    }

    @GetMapping
    public ResponseEntity<List<CalificacionPropiedadDTO>> getAllCalificaciones() {
        List<CalificacionPropiedad> calificaciones = calificacionPropiedadService.getAllCalificaciones();
        return new ResponseEntity<List<CalificacionPropiedadDTO>>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionPropiedadDTO> getCalificacionById(@PathVariable("id") Long id) {
        CalificacionPropiedad calificacion = calificacionPropiedadService.getCalificacionById(id);
        if (calificacion != null) {
            return new ResponseEntity<>(calificacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CalificacionPropiedadDTO> createCalificacion(@RequestBody CalificacionPropiedadDTO calificacionPropiedadDTO) {
        CalificacionPropiedadDTO createdCalificacion = calificacionPropiedadService.createCalificacion(calificacionPropiedadDTO);
        return new ResponseEntity<>(createdCalificacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionPropiedadDTO> updateCalificacion(@PathVariable("id") Long id, @RequestBody CalificacionPropiedadDTO calificacionPropiedadDTO) {
        CalificacionPropiedadDTO updatedCalificacion = calificacionPropiedadService.updateCalificacion(id, calificacionPropiedadDTO);
        if (updatedCalificacion != null) {
            return new ResponseEntity<>(updatedCalificacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable("id") Long id) {
        calificacionPropiedadService.deleteCalificacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
