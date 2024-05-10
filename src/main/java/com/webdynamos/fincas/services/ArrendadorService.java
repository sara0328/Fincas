package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.ArrendadorConPasswordDTO;
import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.repository.ArrendadorRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class ArrendadorService {

    @Autowired
    private final ArrendadorRepository arrendadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    
    public ArrendadorService(ArrendadorRepository arrendadorRepository, ModelMapper modelMapper) {
        this.arrendadorRepository = arrendadorRepository;
        this.modelMapper = modelMapper;
    }

    public ArrendadorDTO crearArrendador(ArrendadorConPasswordDTO arrendadorConPasswordDTO) {
        String passwordCifrada = sha256Hash(arrendadorConPasswordDTO.getPassword());
        arrendadorConPasswordDTO.setPassword(passwordCifrada);
        Arrendador arrendador = this.modelMapper.map(arrendadorConPasswordDTO, Arrendador.class);
        arrendador = arrendadorRepository.save(arrendador);
        return (ArrendadorDTO) this.modelMapper.map(arrendador, ArrendadorDTO.class);
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

    public List<ArrendadorDTO> listarArrendadores() {
        List<Arrendador> arrendadores = arrendadorRepository.findAll();
        List<ArrendadorDTO> arrendadoresDTO = arrendadores.stream()
                .map(arrendador -> modelMapper.map(arrendador,ArrendadorDTO.class))
                .collect(Collectors.toList());
        return arrendadoresDTO;
    }
    

    public ArrendadorDTO obtenerArrendadorPorId(Long id) {
        Optional<Arrendador> arrendadorOptional = arrendadorRepository.findById(id);
        ArrendadorDTO arrendadorDTO = null;
        if(arrendadorOptional != null){
            arrendadorDTO = modelMapper.map(arrendadorOptional.get(), ArrendadorDTO.class);
        }         
        return arrendadorDTO;
    }

    public ArrendadorDTO actualizarArrendador(ArrendadorDTO arrendadorDTO) throws ValidationException{
        
        arrendadorDTO = obtenerArrendadorPorId(arrendadorDTO.getId_arrendador());
        if( arrendadorDTO == null ){
            throw new ValidationException(null);
        }

        Arrendador arrendador = modelMapper.map(arrendadorDTO, Arrendador.class);
        arrendador = arrendadorRepository.save(arrendador);
        arrendadorDTO = modelMapper.map(arrendador, ArrendadorDTO.class);
        return arrendadorDTO;
    }
    
    public boolean deleteArrendador(Long id) {
        if (arrendadorRepository.existsById(id)) {
            arrendadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
