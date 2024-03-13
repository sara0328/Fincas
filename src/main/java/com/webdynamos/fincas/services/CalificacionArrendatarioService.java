package com.webdynamos.fincas.services;

import com.webdynamos.fincas.models.CalificacionArrendatario;
import com.webdynamos.fincas.repository.CalificacionArrendatarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionArrendatarioService {

    private final CalificacionArrendatarioRepository calificacion_arrendatarioRepository;

    public CalificacionArrendatarioService(CalificacionArrendatarioRepository calificacion_arrendatarioRepository)
    {
        this.calificacion_arrendatarioRepository = calificacion_arrendatarioRepository;
    }

    public CalificacionArrendatario CrearArrendador_arrendatario(CalificacionArrendatario calificacion_arrendatario)
    {
        return calificacion_arrendatarioRepository.save(calificacion_arrendatario);
    }

    //Encuentra todos los elementos
    public List<CalificacionArrendatario> ListarArrendador_arrendatario()
    {
        return calificacion_arrendatarioRepository.findAll();
    }

    public CalificacionArrendatario obtenerArrendador_arrendatarioPorId(Long id) {
        return calificacion_arrendatarioRepository.findById(Optional.ofNullable(id).orElseGet(() -> 0L)).orElse(null);
    }

    public CalificacionArrendatario actualizarArrendador_arrendatario(Long id, CalificacionArrendatario calificacion_arrendatario)
    {
        if (calificacion_arrendatarioRepository.existsById(id))
        {
            CalificacionArrendatario cambio = calificacion_arrendatarioRepository.findById(id != null ? id : 0L).orElse(null);

            cambio.setCalificacion(cambio.getCalificacion());
            return calificacion_arrendatarioRepository.save(cambio);
        }

        return null;
    }

    public boolean deleteCalificacionArrendatario(Long id) {
        if (calificacion_arrendatarioRepository.existsById(id)) {
            calificacion_arrendatarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<CalificacionArrendatario> updateCalificacionArrendatario(Long id,
            CalificacionArrendatario calificacionArrendatario) {
        return calificacion_arrendatarioRepository.findById(id);
    }

    public Optional<CalificacionArrendatario> getCalificacionArrendatario(Long id) {
        return calificacion_arrendatarioRepository.findById(id);
    }

}
