package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.dto.SolicitudDTO;
import com.webdynamos.fincas.services.SolicitudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping(produces = "application/json")
    public List<SolicitudDTO> get() {
        return solicitudService.ListarSolicitud();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public SolicitudDTO get(@PathVariable Long id) {
        return solicitudService.obtenerSolicitudPorId(id);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<SolicitudDTO> create(@RequestBody SolicitudDTO solicitudDTO, @AuthenticationPrincipal UserDetails userDetails) {
        // Aquí puedes usar userDetails para obtener información sobre el usuario autenticado
        SolicitudDTO createdSolicitud = solicitudService.CrearSolicitud(solicitudDTO);
        return ResponseEntity.status(201).body(createdSolicitud);
    }

    @PutMapping(produces = "application/json")
    public SolicitudDTO update(@RequestBody SolicitudDTO solicitudDTO) {
        return solicitudService.actualizarSolicitud(solicitudDTO);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) {
        solicitudService.deleteSolicitud(id);
    }
}
