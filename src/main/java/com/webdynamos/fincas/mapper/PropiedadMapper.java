package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.webdynamos.fincas.dto.PropiedadDTO;
import com.webdynamos.fincas.models.Propiedad;

@Mapper
public interface PropiedadMapper {

    PropiedadMapper mapper = Mappers.getMapper(PropiedadMapper.class);

    @Mapping(source = "id_propiedad", target = "id_propiedad")
    @Mapping(source = "calificacion", target = "calificacion")
    @Mapping(source = "precio", target = "precio")
    @Mapping(source = "ubicacion", target = "ubicacion")
    @Mapping(source = "disponibilidad", target = "disponibilidad")
    PropiedadDTO propiedadToPropiedadDTO(Propiedad propiedad);

}
