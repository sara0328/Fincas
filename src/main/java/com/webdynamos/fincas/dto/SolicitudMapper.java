package com.webdynamos.fincas.dto;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.webdynamos.fincas.models.Solicitud;

@Mapper
public interface SolicitudMapper {

    SolicitudMapper mapper = Mappers.getMapper(SolicitudMapper.class);

    @Mapping(source = "id_solicitud", target = "id_solicitud")
    @Mapping(source = "duracion", target = "duracion")
    @Mapping(source = "estado", target = "estado")
    SolicitudDTO solicitudToSolicitudDTO(Solicitud solicitud);
}
