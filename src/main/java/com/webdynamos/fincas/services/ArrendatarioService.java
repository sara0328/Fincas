package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.ArrendatarioDTO;
import com.webdynamos.fincas.dto.ArrendatarioConPasswordDTO;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.repository.ArrendatarioRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public ArrendatarioDTO crearArrendatario(ArrendatarioConPasswordDTO arrendatarioConPasswordDTO) {
        String passwordCifrada = sha256Hash(arrendatarioConPasswordDTO.getPassword());
        arrendatarioConPasswordDTO.setPassword(passwordCifrada);
        Arrendatario arrendatario = this.modelMapper.map(arrendatarioConPasswordDTO, Arrendatario.class);
        arrendatario = arrendatarioRepository.save(arrendatario);
        return (ArrendatarioDTO) this.modelMapper.map(arrendatario, ArrendatarioDTO.class);
    }

    public static String sha256Hash(String input) {
        try {
            // Crear una instancia de MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplicar el hash al input
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convertir los bytes del hash a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción si el algoritmo no está disponible
            e.printStackTrace();
            return null;
        }
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
