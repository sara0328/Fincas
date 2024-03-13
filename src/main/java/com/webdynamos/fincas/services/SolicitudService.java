package com.webdynamos.fincas.services;


import com.webdynamos.fincas.models.Solicitud;
import com.webdynamos.fincas.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.NonNull;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudService (SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    //.save de JPA
    public Solicitud CrearSolicitud(@NonNull Solicitud solicitud)
    {
        return solicitudRepository.save(solicitud);
    }

    //Encuentra todos los elementos
    public List<Solicitud> ListarSolicitud()
    {
        return solicitudRepository.findAll();
    }

    public Solicitud obtenerSolicitudPorId(Long id) {
        if (id != null) {
            return solicitudRepository.findById(id).orElse(null);
        }
        return null;
    }

    public Solicitud actualizarSolicitud(@NonNull Long id, Solicitud solicitud)
    {
        if (solicitudRepository.existsById(id))
        {
            Solicitud cambio = solicitudRepository.findById(id).orElse(null);


            cambio.setDuracion(solicitud.getDuracion());
            cambio.setDuracion(solicitud.getDuracion());
            return solicitudRepository.save(cambio);
        }

        return null;
    }

    public boolean deleteSolicitud(Long id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
