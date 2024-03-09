package com.webdynamos.fincas.services;

import com.webdynamos.fincas.models.Calificacion_arrendatario;
import com.webdynamos.fincas.repository.Calificacion_arrendatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Calificacion_arrendatarioService {

    private final Calificacion_arrendatarioRepository calificacion_arrendatarioRepository;

    @Autowired
    public Calificacion_arrendatarioService(Calificacion_arrendatarioRepository calificacion_arrendatarioRepository)
    {
        this.calificacion_arrendatarioRepository = calificacion_arrendatarioRepository;
    }

    @SuppressWarnings("null")
    public Calificacion_arrendatario CrearArrendador_arrendatario(Calificacion_arrendatario calificacion_arrendatario)
    {
        return calificacion_arrendatarioRepository.save(calificacion_arrendatario);
    }

    //Encuentra todos los elementos
    public List<Calificacion_arrendatario> ListarArrendador_arrendatario()
    {
        return calificacion_arrendatarioRepository.findAll();
    }

    public Calificacion_arrendatario obtenerArrendador_arrendatarioPorId(Long id) {
        return calificacion_arrendatarioRepository.findById(Optional.ofNullable(id).orElse(0L)).orElse(null);
    }

    public Calificacion_arrendatario actualizarArrendador_arrendatario(Long id, Calificacion_arrendatario calificacion_arrendatario)
    {
        if (calificacion_arrendatarioRepository.existsById(id))
        {
            Calificacion_arrendatario cambio = calificacion_arrendatarioRepository.findById(id != null ? id : 0L).orElse(null);

            cambio.setCalificacion(cambio.getCalificacion());
            return calificacion_arrendatarioRepository.save(cambio);
        }

        return null;
    }
}
