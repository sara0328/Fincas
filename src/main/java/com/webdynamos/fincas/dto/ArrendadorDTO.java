package com.webdynamos.fincas.dto;

import com.webdynamos.fincas.models.Arrendador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Setter;
import lombok.Getter;

@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor

public class ArrendadorDTO {

    private Long id_arrendador;
    private int calificacion;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
	public ArrendadorDTO arrendadorToArrendadorDTO(Arrendador updatedEntity) {
        ArrendadorDTO arrendadorDTO = new ArrendadorDTO();
        arrendadorDTO.setId_arrendador(updatedEntity.getId_arrendador());
        arrendadorDTO.setCalificacion(updatedEntity.getCalificacion());
        arrendadorDTO.setNombre(updatedEntity.getNombre());
        arrendadorDTO.setApellido(updatedEntity.getApellido());
        arrendadorDTO.setTelefono(updatedEntity.getTelefono());
        arrendadorDTO.setCorreo(updatedEntity.getCorreo());
        return arrendadorDTO;

	}
}
