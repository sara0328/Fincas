package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.PropiedadDTO;
import com.webdynamos.fincas.models.Propiedad;
import com.webdynamos.fincas.repository.PropiedadRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropiedadService {

    private final PropiedadRepository propiedadRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PropiedadService(PropiedadRepository propiedadRepository, ModelMapper modelMapper) {
        this.propiedadRepository = propiedadRepository;
        this.modelMapper = modelMapper;
    }

    public PropiedadDTO crearPropiedad(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad = propiedadRepository.save(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }

    public List<PropiedadDTO> listarPropiedades() {
        List<Propiedad> propiedades = propiedadRepository.findAll();
        return propiedades.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());
    }

    public PropiedadDTO obtenerPropiedadPorId(Long id) {
        Optional<Propiedad> propiedadOptional = propiedadRepository.findById(id);
        if (propiedadOptional.isPresent()) {
            return modelMapper.map(propiedadOptional.get(), PropiedadDTO.class);
        }
        return null;
    }

    public PropiedadDTO actualizarPropiedad(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad = propiedadRepository.save(propiedad);
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }

    public boolean deletePropiedad(Long id) {
        if (propiedadRepository.existsById(id)) {
            propiedadRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<PropiedadDTO> obtenerMejoresPropiedadesDisponibles() {
        List<Propiedad> propiedades = propiedadRepository.findAll()
                .stream()
                .filter(propiedad -> propiedad.getDisponibilidad() > 0)
                .sorted(Comparator.comparingInt(Propiedad::getCalificacion).reversed())
                .limit(10)
                .collect(Collectors.toList());

        return propiedades.stream()
                .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                .collect(Collectors.toList());
    }
}
