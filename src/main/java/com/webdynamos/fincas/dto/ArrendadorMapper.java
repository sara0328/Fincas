package com.webdynamos.fincas.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.webdynamos.fincas.models.Arrendador;

@Mapper

public interface ArrendadorMapper {

        ArrendadorMapper mapper = Mappers.getMapper(ArrendadorMapper.class);

        @Mapping(source = "id_arrendador", target = "id_arrendador")
        @Mapping(source = "nombre", target = "nombre")
        @Mapping(source = "apellido", target = "apellido")
        @Mapping(source = "correo", target = "correo")
        ArrendadorDTO arrendadorToArrendadorDTO(Arrendador arrendador);
}
