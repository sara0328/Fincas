package com.webdynamos.fincas.services;

import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.models.Arrendador_arrendatario;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.repository.Arrendador_arrendatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Arrendador_arrendatarioService {

    private final Arrendador_arrendatarioRepository arrendador_arrendatarioRepository;

    @Autowired
    public Arrendador_arrendatarioService(Arrendador_arrendatarioRepository arrendador_arrendatarioRepository)
    {
        this.arrendador_arrendatarioRepository = arrendador_arrendatarioRepository;
    }

    public Arrendador_arrendatario CrearArrendador_arrendatario(Arrendador_arrendatario arrendador_arrendatario)
    {
        return arrendador_arrendatarioRepository.save(arrendador_arrendatario);
    }

    //Encuentra todos los elementos
    public List<Arrendador_arrendatario> ListarArrendador_arrendatario()
    {
        return arrendador_arrendatarioRepository.findAll();
    }

    public Arrendador_arrendatario obtenerArrendador_arrendatarioPorId(Long id) {
        return arrendador_arrendatarioRepository.findById(id).orElse(null);
    }

    public Arrendador_arrendatario actualizarArrendador_arrendatario(Long id, Arrendador_arrendatario arrendador_arrendatario)
    {
        if (arrendador_arrendatarioRepository.existsById(id))
        {
            Arrendador_arrendatario cambio = arrendador_arrendatarioRepository.findById(id).orElse(null);

            cambio.setCalificacion(cambio.getCalificacion());
            return arrendador_arrendatarioRepository.save(cambio);
        }

        return null;
    }
}
