package com.webdynamos.fincas.mapper;

import org.mapstruct.Mapper;
import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.models.Arrendador;
import org.mapstruct.Mapping; // Add this import statement

@Mapper(componentModel = "spring")
public interface ArrendadorMapper {

    ArrendadorDTO arrendadorToArrendadorDTO(Arrendador arrendador);

    Arrendador arrendadorDTOToArrendador(ArrendadorDTO arrendadorDTO);
}


