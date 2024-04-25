package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.dto.SolicitudDTO;
import com.webdynamos.fincas.services.SolicitudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    @Autowired
    private final SolicitudService solicitudService;

    
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SolicitudDTO> get(){
        return solicitudService.ListarSolicitud();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO get(@PathVariable Long id){
        return solicitudService.obtenerSolicitudPorId(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO create(@RequestBody SolicitudDTO solicitudDTO){
        return solicitudService.CrearSolicitud(solicitudDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudDTO update(@RequestBody SolicitudDTO solicitudDTO){
        return solicitudService.actualizarSolicitud(solicitudDTO);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Long id){
        solicitudService.deleteSolicitud(id);
    }
}