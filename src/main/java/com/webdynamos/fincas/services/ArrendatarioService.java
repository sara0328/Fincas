package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.ArrendatarioDTO;
import com.webdynamos.fincas.dto.ArrendatarioConPasswordDTO;
import com.webdynamos.fincas.exceptions.ResourceNotFoundException;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.repository.ArrendatarioRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ArrendatarioService(ArrendatarioRepository arrendatarioRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.arrendatarioRepository = arrendatarioRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ArrendatarioDTO crearArrendatario(ArrendatarioConPasswordDTO arrendatarioConPasswordDTO) {
        String passwordCifrada = passwordEncoder.encode(arrendatarioConPasswordDTO.getPassword());
        arrendatarioConPasswordDTO.setPassword(passwordCifrada);
        Arrendatario arrendatario = this.modelMapper.map(arrendatarioConPasswordDTO, Arrendatario.class);
        arrendatario = arrendatarioRepository.save(arrendatario);
        return this.modelMapper.map(arrendatario, ArrendatarioDTO.class);
    }

    public List<ArrendatarioDTO> listarArrendatarios() {
        List<Arrendatario> arrendatarios = arrendatarioRepository.findAll();
        return arrendatarios.stream()
                .map(arrendatario -> modelMapper.map(arrendatario, ArrendatarioDTO.class))
                .collect(Collectors.toList());
    }

    public ArrendatarioDTO obtenerArrendatarioPorId(Long id) {
        Optional<Arrendatario> arrendatarioOptional = arrendatarioRepository.findById(id);
        if (arrendatarioOptional.isPresent()) {
            return modelMapper.map(arrendatarioOptional.get(), ArrendatarioDTO.class);
        }
        return null;
    }

    public ArrendatarioDTO actualizarArrendatario(ArrendatarioDTO arrendatarioDTO) {
        ArrendatarioDTO existingArrendatarioDTO = obtenerArrendatarioPorId(arrendatarioDTO.getId_arrendatario());
        if (existingArrendatarioDTO == null) {
            throw new ResourceNotFoundException("Arrendatario not found");
        }

        Arrendatario arrendatario = modelMapper.map(arrendatarioDTO, Arrendatario.class);
        arrendatario = arrendatarioRepository.save(arrendatario);
        return modelMapper.map(arrendatario, ArrendatarioDTO.class);
    }

    public boolean deleteArrendatario(Long id) {
        if (arrendatarioRepository.existsById(id)) {
            arrendatarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
