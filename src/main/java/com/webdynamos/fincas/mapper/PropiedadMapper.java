package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.webdynamos.fincas.dto.PropiedadDTO;
import com.webdynamos.fincas.models.Propiedad;

@Mapper
public interface PropiedadMapper {

    Propiedad propiedadDTOToPropiedad(PropiedadDTO propiedadDTO);

    PropiedadDTO propiedadToPropiedadDTO(Propiedad propiedad);

}
