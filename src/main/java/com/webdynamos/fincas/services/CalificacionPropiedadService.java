package com.webdynamos.fincas.services;
import com.webdynamos.fincas.dto.CalificacionPropiedadDTO;
import com.webdynamos.fincas.models.CalificacionPropiedad;
import com.webdynamos.fincas.repository.CalificacionPropiedadRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.NonNull;

@Service
public class CalificacionPropiedadService {

    private final CalificacionPropiedadRepository calificacion_propiedadRepository;
    ModelMapper modelMapper;

    @Autowired
    public CalificacionPropiedadService(CalificacionPropiedadRepository calificacion_propiedadRepository, ModelMapper modelMapper)
    {
        this.calificacion_propiedadRepository = calificacion_propiedadRepository;
        this.modelMapper = modelMapper;
    }

    public CalificacionPropiedadDTO CrearArrendador_propiedad(@NonNull CalificacionPropiedadDTO calificacion_propiedadDTO)
    {
        CalificacionPropiedad calificacionPropiedad = modelMapper.map(calificacion_propiedadDTO, CalificacionPropiedad.class);
        calificacionPropiedad = calificacion_propiedadRepository.save(calificacionPropiedad);
        
        return modelMapper.map(calificacionPropiedad, CalificacionPropiedadDTO.class);
    }

    //Encuentra todos los elementos
    public List<CalificacionPropiedadDTO> getAllCalificaciones() {
        List<CalificacionPropiedad> calificacionPropiedads = (List<CalificacionPropiedad>) calificacion_propiedadRepository.findAll();
        List<CalificacionPropiedadDTO> calificacionPropiedadDTOs = calificacionPropiedads.stream().map(calificacionPropiedad -> modelMapper.map(calificacionPropiedad, CalificacionPropiedadDTO.class)).collect(Collectors.toList());
        
        return calificacionPropiedadDTOs;
    }

    public CalificacionPropiedadDTO updateCalificacion(CalificacionPropiedadDTO calificacionPropiedadDTO) {
        CalificacionPropiedad calificacionPropiedad = modelMapper.map(calificacionPropiedadDTO, CalificacionPropiedad.class);
        calificacionPropiedad =calificacion_propiedadRepository.save(calificacionPropiedad);

        return modelMapper.map(calificacionPropiedad,CalificacionPropiedadDTO.class);
    }

    public CalificacionPropiedad createCalificacion(CalificacionPropiedad calificacion) {
        return calificacion_propiedadRepository.save(calificacion);
    }

    public CalificacionPropiedadDTO getCalificacionById(Long id) {
        Optional<CalificacionPropiedad> calificacionPropiedadOptional = calificacion_propiedadRepository.findById(id);
        CalificacionPropiedadDTO calificacionPropiedadDTO = null;

        if(calificacionPropiedadOptional.isPresent()){
            CalificacionPropiedad calificacionPropiedad = calificacionPropiedadOptional.get();
            calificacionPropiedadDTO = modelMapper.map(calificacionPropiedad,CalificacionPropiedadDTO.class);
        }

        return calificacionPropiedadDTO;
    }

    public void deleteCalificacion(Long id) {
        calificacion_propiedadRepository.deleteById(id);
    }
}
