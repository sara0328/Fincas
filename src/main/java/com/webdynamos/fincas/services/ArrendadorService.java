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
        List<Arrendador> arrendadores = arrendadorRepository.findAll();
        return arrendadores.stream()
                .map(arrendadorMapper::arrendadorToArrendadorDTO)
                .collect(Collectors.toList());
    }
    

    public ArrendadorDTO obtenerArrendadorPorId(Long id) {
        Arrendador arrendador = arrendadorRepository.findById(id).orElse(null);
        return arrendador != null ? (ArrendadorDTO) arrendadorMapper.arrendadorToArrendadorDTO(arrendador) : null;
    }

    public ArrendadorDTO actualizarArrendador(ArrendadorDTO arrendadorDTO) {
        // Verificar si el ArrendadorDTO tiene un ID válido
        if (arrendadorDTO.getId_arrendador() != null) {
            // Verificar si el Arrendador con el ID dado existe en la base de datos
            if (arrendadorRepository.existsById(arrendadorDTO.getId_arrendador())) {
                // Obtener el Arrendador existente de la base de datos
                Arrendador existingArrendador = arrendadorRepository.findById(arrendadorDTO.getId_arrendador()).orElse(null);
                // Verificar si se encontró un Arrendador existente
                if (existingArrendador != null) {
                    // Mapear los datos del ArrendadorDTO recibido al Arrendador existente
                    Arrendador arrendador = arrendadorMapper.arrendadorDTOToArrendador(arrendadorDTO);
                    // Guardar los cambios en la base de datos y obtener el Arrendador actualizado
                    arrendador = arrendadorRepository.save(arrendador);
                    // Mapear el Arrendador actualizado a un ArrendadorDTO y devolverlo
                    return arrendadorMapper.arrendadorToArrendadorDTO(arrendador);
                }
            }
        }
        // Si el ArrendadorDTO no tiene un ID válido o no se encuentra en la base de datos, devolver null
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
