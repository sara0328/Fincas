package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import com.webdynamos.fincas.dto.CalificacionPropiedadDTO;
import com.webdynamos.fincas.models.CalificacionPropiedad;

@Mapper(componentModel = "spring")
public interface CalificacionPropiedadMapper {

    CalificacionPropiedadDTO entityToDto(CalificacionPropiedad calificacionPropiedad);

    CalificacionPropiedad dtoToEntity(CalificacionPropiedadDTO calificacionPropiedadDTO);
}
