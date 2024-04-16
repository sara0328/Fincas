package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.dto.CalificacionPropiedadDTO;
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
        List<CalificacionPropiedadDTO> calificacionesDTOs = calificacionPropiedadService.getAllCalificaciones();
        return new ResponseEntity<>(calificacionesDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionPropiedadDTO> getCalificacionById(@PathVariable("id") Long id) {
        CalificacionPropiedadDTO calificacionDTO = calificacionPropiedadService.getCalificacionById(id);
        if (calificacionDTO != null) {
            return new ResponseEntity<>(calificacionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CalificacionPropiedadDTO> createCalificacion(@RequestBody CalificacionPropiedadDTO calificacionPropiedadDTO) {
        CalificacionPropiedadDTO createdCalificacionDTO = calificacionPropiedadService.createCalificacion(calificacionPropiedadDTO);
        return new ResponseEntity<>(createdCalificacionDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionPropiedadDTO> updateCalificacion(@PathVariable("id") Long id, @RequestBody CalificacionPropiedadDTO calificacionPropiedadDTO) {
        CalificacionPropiedadDTO updatedCalificacionDTO = calificacionPropiedadService.updateCalificacion(id, calificacionPropiedadDTO);
        if (updatedCalificacionDTO != null) {
            return new ResponseEntity<>(updatedCalificacionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable("id") Long id) {
        boolean wasDeleted = calificacionPropiedadService.deleteCalificacion(id);
        if (wasDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
