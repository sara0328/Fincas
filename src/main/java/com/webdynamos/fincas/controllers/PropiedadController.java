package com.webdynamos.fincas.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webdynamos.fincas.dto.PropiedadDTO;
import com.webdynamos.fincas.services.PropiedadService;
import java.util.List;


@RestController
@RequestMapping("/propiedades")
public class PropiedadController {

    private PropiedadService propiedadService;

    @Autowired
    public PropiedadController(PropiedadService propiedadService){
        this.propiedadService = propiedadService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO get(@PathVariable Long id){
        return propiedadService.obtenerPropiedadPorId(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PropiedadDTO> get(){
        return propiedadService.ListarPropiedades();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO create(@RequestBody PropiedadDTO propiedadDTO){
        return propiedadService.CrearPropiedad(propiedadDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PropiedadDTO update(@RequestBody PropiedadDTO propiedadDTO){
        return propiedadService.actualizarPropiedad(propiedadDTO);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        propiedadService.deletePropiedad(id);
    }
}