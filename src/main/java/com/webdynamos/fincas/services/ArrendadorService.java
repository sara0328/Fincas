package com.webdynamos.fincas.services;

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

    public ArrendadorDTO crearArrendador(ArrendadorDTO arrendadorDTO) {
        Arrendador arrendador = this.modelMapper.map(arrendadorDTO, Arrendador.class);
        arrendador = arrendadorRepository.save(arrendador);
        return (ArrendadorDTO) this.modelMapper.map(arrendador, ArrendadorDTO.class);
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
