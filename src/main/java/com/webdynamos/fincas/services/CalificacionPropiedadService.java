package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.CalificacionPropiedadDTO;
import com.webdynamos.fincas.mapper.CalificacionPropiedadMapper;
import com.webdynamos.fincas.models.CalificacionPropiedad;
import com.webdynamos.fincas.repository.CalificacionPropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalificacionPropiedadService {

    private final CalificacionPropiedadRepository calificacionPropiedadRepository;
    private final CalificacionPropiedadMapper calificacionPropiedadMapper;

    @Autowired
    public CalificacionPropiedadService(CalificacionPropiedadRepository calificacionPropiedadRepository,
                                        CalificacionPropiedadMapper calificacionPropiedadMapper) {
        this.calificacionPropiedadRepository = calificacionPropiedadRepository;
        this.calificacionPropiedadMapper = calificacionPropiedadMapper;
    }

    public CalificacionPropiedadDTO createCalificacion(CalificacionPropiedadDTO calificacionPropiedadDTO) {
        CalificacionPropiedad calificacionPropiedad = calificacionPropiedadMapper.dtoToEntity(calificacionPropiedadDTO);
        calificacionPropiedad = calificacionPropiedadRepository.save(calificacionPropiedad);
        return calificacionPropiedadMapper.entityToDto(calificacionPropiedad);
    }

    public List<CalificacionPropiedadDTO> getAllCalificaciones() {
        return calificacionPropiedadRepository.findAll().stream()
                .map(calificacionPropiedadMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CalificacionPropiedadDTO getCalificacionById(Long id) {
        return calificacionPropiedadRepository.findById(id)
                .map(calificacionPropiedadMapper::entityToDto)
                .orElse(null);
    }

    public CalificacionPropiedadDTO updateCalificacion(Long id, CalificacionPropiedadDTO calificacionPropiedadDTO) {
        return calificacionPropiedadRepository.findById(id)
                .map(calificacionPropiedad -> {
                    calificacionPropiedad.setCalificacion(calificacionPropiedadDTO.getCalificacion());
                    calificacionPropiedad = calificacionPropiedadRepository.save(calificacionPropiedad);
                    return calificacionPropiedadMapper.entityToDto(calificacionPropiedad);
                })
                .orElseThrow(() -> new RuntimeException("CalificacionPropiedad not found with id: " + id));
    }

    public boolean deleteCalificacion(Long id) {
        if (calificacionPropiedadRepository.existsById(id)) {
            calificacionPropiedadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
