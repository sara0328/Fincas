package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.ArrendadorConPasswordDTO;
import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.repository.ArrendadorRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArrendadorService {

    @Autowired
    private final ArrendadorRepository arrendadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ArrendadorService(ArrendadorRepository arrendadorRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.arrendadorRepository = arrendadorRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean usuarioValido(ArrendadorConPasswordDTO usuarioDTO) {
        System.out.println("Verificando usuario: " + usuarioDTO.getUsername());
        
        Optional<Arrendador> arrendadorOpt = arrendadorRepository.findByUsername(usuarioDTO.getUsername());
        if (arrendadorOpt.isPresent()) {
            Arrendador arrendador = arrendadorOpt.get();
            System.out.println("Usuario encontrado: " + arrendador.getUsername());
            System.out.println("Contraseña proporcionada: " + usuarioDTO.getPassword());
            System.out.println("Contraseña almacenada: " + arrendador.getPassword());
    
            boolean passwordsMatch = passwordEncoder.matches(usuarioDTO.getPassword(), arrendador.getPassword());
            System.out.println("Las contraseñas coinciden: " + passwordsMatch);
    
            return passwordsMatch;
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return false;
    }
    

    public ArrendadorDTO crearArrendador(ArrendadorConPasswordDTO arrendadorConPasswordDTO) {
        String claveCifrada = passwordEncoder.encode(arrendadorConPasswordDTO.getPassword());
        arrendadorConPasswordDTO.setPassword(claveCifrada);
        Arrendador arrendador = this.modelMapper.map(arrendadorConPasswordDTO, Arrendador.class);
        arrendador = arrendadorRepository.save(arrendador);
        return this.modelMapper.map(arrendador, ArrendadorDTO.class);
    }

    public List<ArrendadorDTO> listarArrendadores() {
        List<Arrendador> arrendadores = arrendadorRepository.findAll();
        return arrendadores.stream()
                .map(arrendador -> modelMapper.map(arrendador, ArrendadorDTO.class))
                .collect(Collectors.toList());
    }

    public ArrendadorDTO obtenerArrendadorPorId(Long id) {
        Optional<Arrendador> arrendadorOptional = arrendadorRepository.findById(id);
        return arrendadorOptional.map(arrendador -> modelMapper.map(arrendador, ArrendadorDTO.class)).orElse(null);
    }

    public ArrendadorDTO actualizarArrendador(ArrendadorDTO arrendadorDTO) {
        Optional<Arrendador> existingArrendadorOpt = arrendadorRepository.findById(arrendadorDTO.getId_arrendador());
        if (existingArrendadorOpt.isPresent()) {
            Arrendador arrendador = modelMapper.map(arrendadorDTO, Arrendador.class);
            arrendador = arrendadorRepository.save(arrendador);
            return modelMapper.map(arrendador, ArrendadorDTO.class);
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
