package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import com.webdynamos.fincas.dto.CalificacionArrendatarioDTO;
import com.webdynamos.fincas.models.CalificacionArrendatario;

@Mapper(componentModel = "spring")
public interface CalificacionArrendatarioMapper {

    CalificacionArrendatarioDTO entityToDto(CalificacionArrendatario calificacionArrendatario);

    CalificacionArrendatario dtoToEntity(CalificacionArrendatarioDTO calificacionArrendatarioDTO);
}

