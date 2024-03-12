package com.webdynamos.fincas.services;


import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.models.Propiedad;
import com.webdynamos.fincas.repository.ArrendatarioRepository;
import com.webdynamos.fincas.repository.PropiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.NonNull;

@Service
public class PropiedadService {
    private final PropiedadRepository propiedadRepository;

    @Autowired
        public PropiedadService (PropiedadRepository propiedadRepository) {
        this.propiedadRepository = propiedadRepository;
    }


    //.save de JPA
    @NonNull
    public Propiedad CrearPropiedad(@NonNull Propiedad propiedad)
    {
        return propiedadRepository.save(propiedad);
    }

    //Encuentra todos los elementos
    public List<Propiedad> ListarPropiedades()
    {
        return propiedadRepository.findAll();
    }

    public Propiedad obtenerPropiedadPorId(@NonNull Long id) {
        return propiedadRepository.findById(id).orElse(null);
    }


    public Propiedad actualizarPropiedad(@NonNull Long id, Propiedad propiedad)
    {
        if (propiedadRepository.existsById(id))
        {
            Propiedad cambio = propiedadRepository.findById(id).orElse(null);


            cambio.setPrecio(propiedad.getPrecio());
            cambio.setUbicacion(propiedad.getUbicacion());
            cambio.setDisponibilidad(propiedad.getDisponibilidad());
            return propiedadRepository.save(cambio);
        }

        return null;
    }


    @SuppressWarnings("null")
    public boolean deletePropiedad(Long id) {
        if (propiedadRepository.existsById(id))
        {
            propiedadRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
