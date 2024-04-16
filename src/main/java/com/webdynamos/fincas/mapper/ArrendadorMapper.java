package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.models.Arrendador;

@Mapper(componentModel = "spring")
public interface ArrendadorMapper {
    
    @Mapping(source = "id_arrendador", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target = "apellido")
    @Mapping(source = "correo", target = "correo")
    ArrendadorDTO arrendadorToArrendadorDTO(Arrendador arrendador);
}
