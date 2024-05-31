package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.dto.PropiedadDTO;
import com.webdynamos.fincas.services.PropiedadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    public PropiedadController(PropiedadService propiedadService) {
        this.propiedadService = propiedadService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO get(@PathVariable Long id) {
        return propiedadService.obtenerPropiedadPorId(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO> get() {
        return propiedadService.listarPropiedades();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PropiedadDTO> create(@RequestBody PropiedadDTO propiedadDTO) {
        PropiedadDTO createdPropiedadDTO = propiedadService.crearPropiedad(propiedadDTO);
        return new ResponseEntity<>(createdPropiedadDTO, HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PropiedadDTO> update(@RequestBody PropiedadDTO propiedadDTO) {
        PropiedadDTO updatedPropiedadDTO = propiedadService.actualizarPropiedad(propiedadDTO);
        return new ResponseEntity<>(updatedPropiedadDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean isDeleted = propiedadService.deletePropiedad(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/mejores", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO> obtenerMejoresPropiedadesDisponibles() {
        return propiedadService.obtenerMejoresPropiedadesDisponibles();
    }


}
