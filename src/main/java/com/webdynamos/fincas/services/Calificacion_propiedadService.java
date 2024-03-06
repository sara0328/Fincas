package com.webdynamos.fincas.services;
import com.webdynamos.fincas.models.Calificacion_propiedad;
import com.webdynamos.fincas.repository.Calificacion_propiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Calificacion_propiedadService {

    private final Calificacion_propiedadRepository calificacion_propiedadRepository;

    @Autowired
    public Calificacion_propiedadService(Calificacion_propiedadRepository calificacion_propiedadRepository)
    {
        this.calificacion_propiedadRepository = calificacion_propiedadRepository;
    }

    public Calificacion_propiedad CrearArrendador_propiedad(Calificacion_propiedad calificacion_propiedad)
    {
        return calificacion_propiedadRepository.save(calificacion_propiedad);
    }

    //Encuentra todos los elementos
    public List<Calificacion_propiedad> ListarArrendador_propiedad()
    {
        return calificacion_propiedadRepository.findAll();
    }

    public Calificacion_propiedad obtenerArrendador_propiedadPorId(Long id) {
        return calificacion_propiedadRepository.findById(id).orElse(null);
    }

}
