package com.webdynamos.fincas.services;


import com.webdynamos.fincas.dto.SolicitudDTO;
import com.webdynamos.fincas.models.Solicitud;
import com.webdynamos.fincas.repository.SolicitudRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.NonNull;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    ModelMapper modelMapper;

    @Autowired
    public SolicitudService (SolicitudRepository solicitudRepository, ModelMapper modelMapper) {
        this.solicitudRepository = solicitudRepository;
        this.modelMapper = modelMapper;
    }

    //.save de JPA
    public SolicitudDTO CrearSolicitud(@NonNull SolicitudDTO solicitudDTO)
    {
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        solicitud = solicitudRepository.save(solicitud);

        return modelMapper.map(solicitud, SolicitudDTO.class);
    }

    //Encuentra todos los elementos
    public List<SolicitudDTO> ListarSolicitud()
    {
        List<Solicitud> solicituds = (List<Solicitud>) solicitudRepository.findAll();
        List<SolicitudDTO> solicitudDTOs = solicituds.stream().map(solicitud -> modelMapper.map(solicitud, SolicitudDTO.class)).collect(Collectors.toList());

        return solicitudDTOs;
    }

    public SolicitudDTO obtenerSolicitudPorId(Long id) {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);
        SolicitudDTO solicitudDTO = null;
        
        if (solicitudOptional.isPresent()) {
            Solicitud solicitud = solicitudOptional.get();
            solicitudDTO = modelMapper.map(solicitud, SolicitudDTO.class);
        }

        return solicitudDTO;
    }

    public SolicitudDTO actualizarSolicitud(SolicitudDTO solicitudDTO)
    {
        Solicitud solicitud = modelMapper.map(solicitudDTO, Solicitud.class);
        solicitud = solicitudRepository.save(solicitud);

        return modelMapper.map(solicitud, SolicitudDTO.class);
    }

    public boolean deleteSolicitud(Long id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
