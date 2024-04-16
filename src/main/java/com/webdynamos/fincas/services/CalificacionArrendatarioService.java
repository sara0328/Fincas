package com.webdynamos.fincas.services;

import com.webdynamos.fincas.dto.CalificacionArrendatarioDTO;
import com.webdynamos.fincas.mapper.CalificacionArrendatarioMapper;
import com.webdynamos.fincas.models.CalificacionArrendatario;
import com.webdynamos.fincas.repository.CalificacionArrendatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalificacionArrendatarioService {

    private final CalificacionArrendatarioRepository calificacionArrendatarioRepository;
    private final CalificacionArrendatarioMapper calificacionArrendatarioMapper;

    @Autowired
    public CalificacionArrendatarioService(CalificacionArrendatarioRepository calificacionArrendatarioRepository,
                                           CalificacionArrendatarioMapper calificacionArrendatarioMapper) {
        this.calificacionArrendatarioRepository = calificacionArrendatarioRepository;
        this.calificacionArrendatarioMapper = calificacionArrendatarioMapper;
    }

    public CalificacionArrendatarioDTO crearCalificacionArrendatario(CalificacionArrendatarioDTO calificacionArrendatarioDTO) {
        CalificacionArrendatario calificacionArrendatario = calificacionArrendatarioMapper.dtoToEntity(calificacionArrendatarioDTO);
        calificacionArrendatario = calificacionArrendatarioRepository.save(calificacionArrendatario);
        return calificacionArrendatarioMapper.entityToDto(calificacionArrendatario);
    }

    public List<CalificacionArrendatarioDTO> listarCalificacionArrendatario() {
        return calificacionArrendatarioRepository.findAll().stream()
                .map(calificacionArrendatarioMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CalificacionArrendatarioDTO obtenerCalificacionArrendatarioPorId(Long id) {
        return calificacionArrendatarioRepository.findById(id)
                .map(calificacionArrendatarioMapper::entityToDto)
                .orElse(null);
    }

    // Remove the nested class declaration
    // public class CalificacionArrendatarioService {

        // ... other service methods ...

        public Optional<CalificacionArrendatario> getCalificacionArrendatario(Long id) {
            // This method retrieves an Optional containing the calificacionArrendatario if found,
            // or an empty Optional otherwise.
            return calificacionArrendatarioRepository.findById(id);
        }

        public Optional<CalificacionArrendatario> actualizarCalificacionArrendatario(Long id, CalificacionArrendatario calificacionArrendatario) {
            // This method first checks if a CalificacionArrendatario with the given id exists.
            // If it does, it updates and saves it, then returns an Optional containing the updated calificacionArrendatario.
            // If it doesn't, it returns an empty Optional.
            return calificacionArrendatarioRepository.findById(id)
                    .map(existingCalificacion -> {
                        existingCalificacion.setCalificacion(calificacionArrendatario.getCalificacion());
                        return calificacionArrendatarioRepository.save(existingCalificacion);
                    });
        }

        public boolean deleteCalificacionArrendatario(Long id) {
            // This method attempts to delete the CalificacionArrendatario with the given id.
            // It returns true if the entity is found and deleted, false otherwise.
            return calificacionArrendatarioRepository.findById(id)
                    .map(calificacion -> {
                        calificacionArrendatarioRepository.delete(calificacion);
                        return true;
                    }).orElse(false);
        }
    // }

}
