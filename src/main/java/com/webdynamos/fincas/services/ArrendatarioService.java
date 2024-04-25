package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.ArrendatarioDTO;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.repository.ArrendatarioRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArrendatarioService {

    @Autowired
    private final ArrendatarioRepository arrendatarioRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public ArrendatarioService(ArrendatarioRepository arrendatarioRepository, ModelMapper modelMapper) {
        this.arrendatarioRepository = arrendatarioRepository;
        this.modelMapper = modelMapper;
    }

    public ArrendatarioDTO crearArrendatario(ArrendatarioDTO arrendatarioDTO) {
        Arrendatario arrendatario = this.modelMapper.map(arrendatarioDTO, Arrendatario.class);
        arrendatario = arrendatarioRepository.save(arrendatario);
        return (ArrendatarioDTO) this.modelMapper.map(arrendatario, ArrendatarioDTO.class);
    }

    public List<ArrendatarioDTO> listarArrendatarios() {
        List<Arrendatario> arrendatarios = arrendatarioRepository.findAll();
        List<ArrendatarioDTO> arrendatariosDTO = arrendatarios.stream()
                .map(arrendatario -> modelMapper.map(arrendatario,ArrendatarioDTO.class))
                .collect(Collectors.toList());
        return arrendatariosDTO;
    }

    public ArrendatarioDTO obtenerArrendatarioPorId(Long id) {
        Optional<Arrendatario> arrendatarioOptional = arrendatarioRepository.findById(id);
        ArrendatarioDTO arrendatarioDTO = null;
        if(arrendatarioOptional != null){
            arrendatarioDTO = modelMapper.map(arrendatarioOptional.get(), ArrendatarioDTO.class);
        }         
        return arrendatarioDTO;
    }

    public ArrendatarioDTO actualizarArrendatario(ArrendatarioDTO arrendatarioDTO) throws ValidationException{
        
        arrendatarioDTO = obtenerArrendatarioPorId(arrendatarioDTO.getId_arrendatario());
        if( arrendatarioDTO == null ){
            throw new ValidationException(null);
        }

        Arrendatario arrendatario = modelMapper.map(arrendatarioDTO, Arrendatario.class);
        arrendatario = arrendatarioRepository.save(arrendatario);
        arrendatarioDTO = modelMapper.map(arrendatario, ArrendatarioDTO.class);
        return arrendatarioDTO;
    }

    public boolean deleteArrendatario(Long id) {
        if (arrendatarioRepository.existsById(id)) {
            arrendatarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
