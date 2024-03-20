package com.webdynamos.fincas.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.webdynamos.fincas.models.Arrendatario;

@Mapper
public interface ArrendatarioMapper {

            ArrendatarioMapper mapper = Mappers.getMapper(ArrendatarioMapper.class);

            @Mapping(source = "id_arrendatario", target = "id_arrendatario")
            @Mapping(source = "nombre", target = "nombre")
            @Mapping(source = "apellido", target = "apellido")
            @Mapping(source = "telefono", target = "telefono")
            @Mapping(source = "correo", target = "correo")
            ArrendatarioDTO arrendatarioToArrendatarioDTO(Arrendatario arrendatario);
}
