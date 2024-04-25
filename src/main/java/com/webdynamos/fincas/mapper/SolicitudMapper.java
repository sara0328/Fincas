package com.webdynamos.fincas.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.webdynamos.fincas.dto.SolicitudDTO;
import com.webdynamos.fincas.models.Solicitud;

@Mapper
public interface SolicitudMapper {

    Solicitud solicitudDTOtoSolicitud(SolicitudDTO solicitudDTO);
    SolicitudDTO solicitudToSolicitudDTO(Solicitud solicitud);
}
