package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.ArrendatarioDTO;
import com.webdynamos.fincas.mapper.ArrendatarioMapper;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.repository.ArrendatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArrendatarioService {

    private final ArrendatarioRepository arrendatarioRepository;
    private final ArrendatarioMapper arrendatarioMapper;

    @Autowired
    public ArrendatarioService(ArrendatarioRepository arrendatarioRepository, ArrendatarioMapper arrendatarioMapper) {
        this.arrendatarioRepository = arrendatarioRepository;
        this.arrendatarioMapper = arrendatarioMapper;
    }

    public ArrendatarioDTO crearArrendatario(ArrendatarioDTO arrendatarioDTO) {
        Arrendatario arrendatario = arrendatarioMapper.arrendatarioDTOToArrendatario(arrendatarioDTO);
        Arrendatario savedArrendatario = arrendatarioRepository.save(arrendatario);
        return arrendatarioMapper.arrendatarioToArrendatarioDTO(savedArrendatario);
    }

    public List<ArrendatarioDTO> listarArrendatarios() {
        return arrendatarioRepository.findAll().stream()
                .map(arrendatarioMapper::arrendatarioToArrendatarioDTO)
                .collect(Collectors.toList());
    }

    public ArrendatarioDTO obtenerArrendatarioPorId(Long id) {
        return arrendatarioRepository.findById(id)
                .map(arrendatarioMapper::arrendatarioToArrendatarioDTO)
                .orElse(null);
    }

    public ArrendatarioDTO actualizarArrendatario(Long id, ArrendatarioDTO arrendatarioDTO) {
        if (arrendatarioRepository.existsById(id)) {
            Arrendatario arrendatario = arrendatarioMapper.arrendatarioDTOToArrendatario(arrendatarioDTO);
            arrendatario.setId(id); // Ensure the ID is set so the entity is updated
            Arrendatario updatedArrendatario = arrendatarioRepository.save(arrendatario);
            return arrendatarioMapper.arrendatarioToArrendatarioDTO(updatedArrendatario);
        }
        return null;
    }

    public boolean deleteArrendatario(Long id) {
        if (arrendatarioRepository.existsById(id)) {
            arrendatarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
