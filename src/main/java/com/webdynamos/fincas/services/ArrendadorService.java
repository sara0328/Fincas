package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.ArrendadorConPasswordDTO;
import com.webdynamos.fincas.dto.ArrendadorDTO;
import com.webdynamos.fincas.dto.UsuarioDTO;
import com.webdynamos.fincas.enums.ROLE;
import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.repository.ArrendadorRepository;
import com.webdynamos.fincas.security.SecurityConfig;

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

    private PasswordService passwordService;

    public ArrendadorService(ArrendadorRepository arrendadorRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, PasswordService passwordService) {
        this.arrendadorRepository = arrendadorRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.passwordService = passwordService;
    }

    public boolean usuarioValido(ArrendadorConPasswordDTO usuarioDTO) {
        System.out.println("Verificando usuario: " + usuarioDTO.getUsername());
        
        Optional<Arrendador> arrendadorOpt = arrendadorRepository.findByUsername(usuarioDTO.getUsername());
        if (arrendadorOpt.isPresent()) {
            Arrendador arrendador = arrendadorOpt.get();
            System.out.println("Usuario encontrado: " + arrendador.getUsername());
            System.out.println("Contraseña proporcionada: " + usuarioDTO.getPassword());
            System.out.println("Contraseña almacenada: " + arrendador.getPassword());

            
            String claveCifrada = passwordService.encryptPassword(usuarioDTO.getPassword());
            if(claveCifrada.equals(arrendador.getPassword())){
                System.out.println("Las contraseñas coinciden: ");
            }
            boolean passwordsMatch = claveCifrada.equals(arrendador.getPassword());
            System.out.println(claveCifrada + " --- "+arrendador.getPassword());
            System.out.println("Las contraseñas coinciden: " + passwordsMatch);
    
            return passwordsMatch;
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return false;
    }
    

    public ROLE getUserRole(String username){
        Optional<Arrendador> arrendadorOpt = arrendadorRepository.findByUsername(username);
        if (arrendadorOpt.isPresent()) {
            Arrendador arrendador = arrendadorOpt.get();
            System.out.println("Usuario encontrado: " + arrendador.getUsername());
            System.out.println("Rol de usuario: " + arrendador.getRole());

            ROLE role = arrendador.getRole();
            return role;
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return null;
    }

    public ArrendadorDTO registrarComoArrendador(ArrendadorConPasswordDTO arrendadorConPasswordDTO){
        String claveCifrada = passwordService.encryptPassword(arrendadorConPasswordDTO.getPassword());
        arrendadorConPasswordDTO.setPassword(claveCifrada);
        Arrendador arrendador = this.modelMapper.map(arrendadorConPasswordDTO, Arrendador.class);
        arrendador.setRole(ROLE.ARRENDADOR);
        return this.modelMapper.map(arrendador, ArrendadorDTO.class);
    }

    public ArrendadorDTO registrarComoArrendatario(ArrendadorConPasswordDTO arrendadorConPasswordDTO){
        String claveCifrada = passwordService.encryptPassword(arrendadorConPasswordDTO.getPassword());
        arrendadorConPasswordDTO.setPassword(claveCifrada);
        Arrendador arrendador = this.modelMapper.map(arrendadorConPasswordDTO, Arrendador.class);
        arrendador.setRole(ROLE.ARRENDATARIO);
        return this.modelMapper.map(arrendador, ArrendadorDTO.class);
    }

    public ArrendadorDTO crearArrendador(ArrendadorConPasswordDTO arrendadorConPasswordDTO) {
        //String claveCifrada = passwordEncoder.encode(arrendadorConPasswordDTO.getPassword());
        String claveCifrada = passwordService.encryptPassword(arrendadorConPasswordDTO.getPassword());
        System.out.println("Rol del usuarioDTO: "+arrendadorConPasswordDTO.getRole().name());
        arrendadorConPasswordDTO.setPassword(claveCifrada);        
        Arrendador arrendador = this.modelMapper.map(arrendadorConPasswordDTO, Arrendador.class);
        System.out.println("Rol del usuario instanciado: "+arrendador.getRole().name());
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
