package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.models.Arrendador;
import org.mapstruct.Mapping; // Add this import statement

@Mapper(componentModel = "spring")
public interface ArrendadorMapper {

    @Mapping(source = "id_arrendador", target = "id_arrendador")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "apellido", target = "apellido")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "calificacion", target = "calificacion")
    ArrendadorDTO arrendadorToArrendadorDTO(Arrendador arrendador);
}


