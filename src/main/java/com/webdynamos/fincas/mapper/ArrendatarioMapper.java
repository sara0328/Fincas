package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import com.webdynamos.fincas.dto.ArrendatarioDTO;
import com.webdynamos.fincas.models.Arrendatario;

@Mapper(componentModel = "spring")
public interface ArrendatarioMapper {

    ArrendatarioDTO arrendatarioToArrendatarioDTO(Arrendatario arrendatario);

    Arrendatario arrendatarioDTOToArrendatario(ArrendatarioDTO arrendatarioDTO);
}
