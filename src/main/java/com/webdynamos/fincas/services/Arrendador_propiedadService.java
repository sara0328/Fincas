package com.webdynamos.fincas.services;
import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.models.Arrendador_arrendatario;
import com.webdynamos.fincas.models.Arrendador_propiedad;
import com.webdynamos.fincas.repository.ArrendadorRepository;
import com.webdynamos.fincas.repository.Arrendador_propiedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Arrendador_propiedadService {

    private final Arrendador_propiedadRepository arrendador_propiedadRepository;

    @Autowired
    public Arrendador_propiedadService (Arrendador_propiedadRepository arrendador_propiedadRepository)
    {
        this.arrendador_propiedadRepository = arrendador_propiedadRepository;
    }

    public Arrendador_propiedad CrearArrendador_propiedad(Arrendador_propiedad arrendador_propiedad)
    {
        return arrendador_propiedadRepository.save(arrendador_propiedad);
    }

    //Encuentra todos los elementos
    public List<Arrendador_propiedad> ListarArrendador_propiedad()
    {
        return arrendador_propiedadRepository.findAll();
    }

    public Arrendador_propiedad obtenerArrendador_propiedadPorId(Long id) {
        return arrendador_propiedadRepository.findById(id).orElse(null);
    }

}
