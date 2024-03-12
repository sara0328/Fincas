package com.webdynamos.fincas.services;
import com.webdynamos.fincas.models.Calificacion_propiedad;
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

    public Calificacion_propiedad CrearArrendador_propiedad(@NonNull Calificacion_propiedad calificacion_propiedad)
    {
        return calificacion_propiedadRepository.save(calificacion_propiedad);
    }

    //Encuentra todos los elementos
    public List<Calificacion_propiedad> getAllCalificaciones() {
        return calificacion_propiedadRepository.findAll();
    }

    @SuppressWarnings("null")
    public <CalificacionPropiedad> Calificacion_propiedad updateCalificacion(Long id,
            CalificacionPropiedad calificacion) {
        return calificacion_propiedadRepository.findById(id)
            .map(calificacionPropiedad -> {
                calificacionPropiedad.setCalificacion(calificacion);
                return calificacion_propiedadRepository.save(calificacionPropiedad);
            })
            .orElseThrow(() -> new RuntimeException("Calificacion_propiedad not found with id: " + id));
    }

    public Calificacion_propiedad createCalificacion(CalificacionPropiedadService calificacion) {
        return calificacion_propiedadRepository.save(calificacion);
    }

    @SuppressWarnings("null")
    public <CalificacionPropiedad> Calificacion_propiedad getCalificacionById(Long id) {
        return calificacion_propiedadRepository.findById(id).orElse(null);
        
    }

    @SuppressWarnings("null")
    public void deleteCalificacion(Long id) {
        calificacion_propiedadRepository.deleteById(id);
    }}
