package com.webdynamos.fincas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.dto.ArrendadorConPasswordDTO;
import org.springframework.http.MediaType;
import com.webdynamos.fincas.services.ArrendadorService;

@RestController
@RequestMapping("/arrendadores")
public class ArrendadorController {

    @Autowired
    private ArrendadorService arrendadorService;

    @GetMapping("/{id}")
    public ResponseEntity<ArrendadorDTO> obtenerArrendadorPorId(@PathVariable Long id) {
        ArrendadorDTO arrendadorDTO = arrendadorService.obtenerArrendadorPorId(id);
        if (arrendadorDTO != null) {
            return new ResponseEntity<>(arrendadorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArrendadorDTO> get(){
        return arrendadorService.listarArrendadores();
    }

    @PostMapping
    public ResponseEntity<ArrendadorDTO> createArrendador(@RequestBody ArrendadorConPasswordDTO arrendadorConPasswordDTO) {
        ArrendadorDTO createdArrendadorDTO = arrendadorService.crearArrendador(arrendadorConPasswordDTO);
        return new ResponseEntity<>(createdArrendadorDTO, HttpStatus.CREATED);
    }
/*
    @PostMapping
    public ResponseEntity<ArrendadorDTO> registrarArrendador(@RequestBody ArrendadorConPasswordDTO arrendadorConPasswordDTO) {
        ArrendadorDTO createdArrendadorDTO = arrendadorService.registrarComoArrendador(arrendadorConPasswordDTO);
        return new ResponseEntity<>(createdArrendadorDTO, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ArrendadorDTO> registrarArrendatario(@RequestBody ArrendadorConPasswordDTO arrendadorConPasswordDTO) {
        ArrendadorDTO createdArrendadorDTO = arrendadorService.registrarComoArrendatario(arrendadorConPasswordDTO);
        return new ResponseEntity<>(createdArrendadorDTO, HttpStatus.CREATED);
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<ArrendadorDTO> updateArrendador(@RequestBody ArrendadorDTO arrendadorDTO) {
        ArrendadorDTO updatedArrendadorDTO = arrendadorService.actualizarArrendador(arrendadorDTO);
        if (updatedArrendadorDTO != null) {
            return new ResponseEntity<>(updatedArrendadorDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArrendador(@PathVariable Long id) {
        boolean deleted = arrendadorService.deleteArrendador(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
