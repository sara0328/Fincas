package com.webdynamos.fincas.services;
import com.webdynamos.fincas.models.CalificacionPropiedad;
import com.webdynamos.fincas.repository.CalificacionPropiedadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.NonNull;

@Service
public class CalificacionPropiedadService {

    private final CalificacionPropiedadRepository calificacion_propiedadRepository;

    public CalificacionPropiedadService(CalificacionPropiedadRepository calificacion_propiedadRepository)
    {
        this.calificacion_propiedadRepository = calificacion_propiedadRepository;
    }

    public CalificacionPropiedad CrearArrendador_propiedad(@NonNull CalificacionPropiedad calificacion_propiedad)
    {
        return calificacion_propiedadRepository.save(calificacion_propiedad);
    }

    //Encuentra todos los elementos
    public List<CalificacionPropiedad> getAllCalificaciones() {
        return calificacion_propiedadRepository.findAll();
    }

    public CalificacionPropiedad updateCalificacion(Long id, CalificacionPropiedad calificacion) {
        return calificacion_propiedadRepository.findById(id)
            .map(calificacionPropiedad -> {
                calificacionPropiedad.setCalificacion(calificacion.getCalificacion()); // Update the line to set the calificacion property
                return calificacion_propiedadRepository.save(calificacionPropiedad);
            })
            .orElseThrow(() -> new RuntimeException("Calificacion_propiedad not found with id: " + id));
    }

    public CalificacionPropiedad createCalificacion(CalificacionPropiedad calificacion) {
        return calificacion_propiedadRepository.save(calificacion);
    }

    public CalificacionPropiedad getCalificacionById(Long id) {
        return calificacion_propiedadRepository.findById(id).orElse(null);
    }

    public void deleteCalificacion(Long id) {
        calificacion_propiedadRepository.deleteById(id);
    }
}
