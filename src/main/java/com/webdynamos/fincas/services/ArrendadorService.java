package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.mapper.ArrendadorMapper;
import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.repository.ArrendadorRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;

@Service
public class ArrendadorService {

    private final ArrendadorRepository arrendadorRepository;
    private final ArrendadorMapper arrendadorMapper; // Injected MapStruct mapper
    ModelMapper modelMapper;

    @Autowired
    public ArrendadorService(ArrendadorRepository arrendadorRepository, ArrendadorMapper arrendadorMapper) {
        this.arrendadorRepository = arrendadorRepository;
        this.arrendadorMapper = arrendadorMapper; // Mapper is being assigned
    }

    public ArrendadorDTO crearArrendador(Arrendador arrendador) {
        Arrendador savedArrendador = arrendadorRepository.save(arrendador);
        return (ArrendadorDTO) arrendadorMapper.arrendadorToArrendadorDTO(savedArrendador);
    }

    public List<ArrendadorDTO> listarArrendadores() {
        List<Arrendador> arrendadores = (List<Arrendador>) arrendadorRepository.findAll();
        List<ArrendadorDTO> arrendadorDTOs = arrendadores.stream().map(arrendador -> modelMapper.map(arrendador, ArrendadorDTO.class)).collect(Collectors.toList());
        return arrendadorDTOs;
    }

    public ArrendadorDTO obtenerArrendadorPorId(Long id) {
        Arrendador arrendador = arrendadorRepository.findById(id).orElse(null);
        return arrendador != null ? (ArrendadorDTO) arrendadorMapper.arrendadorToArrendadorDTO(arrendador) : null;
    }

    public ArrendadorDTO actualizarArrendador(@NonNull Long id, Arrendador arrendador) {
        if (arrendadorRepository.existsById(id)) {
            Arrendador existingArrendador = arrendadorRepository.findById(id).orElse(null);
            if (existingArrendador != null) {
                existingArrendador.setNombre(arrendador.getNombre());
                existingArrendador.setApellido(arrendador.getApellido());
                existingArrendador.setCorreo(arrendador.getCorreo());
                existingArrendador.setTelefono(arrendador.getTelefono());
                Arrendador updatedEntity = arrendadorRepository.save(existingArrendador);
                return (ArrendadorDTO) arrendadorMapper.arrendadorToArrendadorDTO(updatedEntity); // Update the method signature to accept an Arrendador object
            }
        }
        return null;
    }

    public boolean deleteArrendador(Long id) {
        if (arrendadorRepository.existsById(id)) {
            arrendadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
