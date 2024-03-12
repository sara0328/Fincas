package com.webdynamos.fincas.services;

// import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.models.Arrendatario;
import com.webdynamos.fincas.repository.ArrendatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.NonNull;

@Service
public class ArrendatarioService {


    private final ArrendatarioRepository arrendatarioRepository;

    @Autowired
    public ArrendatarioService (ArrendatarioRepository arrendatarioRepository) {
        this.arrendatarioRepository = arrendatarioRepository;
    }

    @SuppressWarnings("null")
    @NonNull
    public Arrendatario CrearArrendatario(Arrendatario arrendatario)
    {
        return arrendatarioRepository.save(arrendatario);
    }


    //Encuentra todos los elementos
    public List<Arrendatario> ListarArrendatarios()
    {
        return arrendatarioRepository.findAll();
    }

    //Obtener por ID
    @SuppressWarnings("null")
    public Arrendatario obtenerArrendatarioPorId(Long id)
    {
        return arrendatarioRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Arrendatario actualizarArrendatario(Long id, Arrendatario arrendatario)
    {
        if (arrendatarioRepository.existsById(id))
        {
            Arrendatario cambio = arrendatarioRepository.findById(id).orElse(null);

            cambio.setNombre(arrendatario.getNombre());
            cambio.setApellido(arrendatario.getApellido());
            cambio.setCorreo(arrendatario.getCorreo());
            cambio.setTelefono(arrendatario.getTelefono());
            return arrendatarioRepository.save(cambio);
        }

        return null;
    }

    @SuppressWarnings("null")
    public boolean deleteArrendatario(Long id) {
        if (arrendatarioRepository.existsById(id))
        {
            arrendatarioRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
