package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.models.Arrendador;
import org.mapstruct.Mapping; // Add this import statement

@Mapper(componentModel = "spring")
public interface ArrendadorMapper {
    
    @Mapping(source = "idArrendador", target = "idArrendador") // Update the target property name to "idArrendador"
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target = "apellido")
    @Mapping(source = "correo", target = "email") // Update the target property name to "email"
    ArrendadorDTO arrendadorToArrendadorDTO(Arrendador arrendador);
}
