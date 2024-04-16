package com.webdynamos.fincas.dto;

import org.mapstruct.Mapper;
import com.webdynamos.fincas.models.Arrendatario;

@Mapper(componentModel = "spring")
public interface ArrendatarioMapper {

    ArrendatarioDTO arrendatarioToArrendatarioDTO(Arrendatario arrendatario);

    Arrendatario arrendatarioDTOToArrendatario(ArrendatarioDTO arrendatarioDTO);
}
