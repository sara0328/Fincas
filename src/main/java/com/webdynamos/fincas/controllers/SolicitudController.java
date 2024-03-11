package com.webdynamos.fincas.controllers;

import com.webdynamos.fincas.models.Solicitud;
import com.webdynamos.fincas.services.SolicitudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    public ResponseEntity<List<Solicitud>> getAllSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.ListarSolicitud();

        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable Long id) {
        Solicitud solicitud = solicitudService.obtenerSolicitudPorId(id);
        if (solicitud != null) {
            return new ResponseEntity<>(solicitud, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Solicitud> createSolicitud(@RequestBody Solicitud solicitud) {
        Solicitud createdSolicitud = solicitudService.CrearSolicitud(solicitud);
        return new ResponseEntity<>(createdSolicitud, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitud> updateSolicitud(@PathVariable Long id, @RequestBody Solicitud solicitud) {
        Solicitud updatedSolicitud = solicitudService.actualizarSolicitud(id, solicitud);
        if (updatedSolicitud != null) {
            return new ResponseEntity<>(updatedSolicitud, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        boolean deleted = solicitudService.deleteSolicitud(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}