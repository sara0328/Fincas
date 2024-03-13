package com.webdynamos.fincas.services;
import com.webdynamos.fincas.models.Arrendador;
import com.webdynamos.fincas.repository.ArrendadorRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import lombok.NonNull;

@Service
public class ArrendadorService {

    private final ArrendadorRepository arrendadorRepository;

    public ArrendadorService (ArrendadorRepository arrendadorRepository) {
        this.arrendadorRepository = arrendadorRepository;
    }


    public Arrendador CrearArrendador(Arrendador arrendador)
    {
        return arrendadorRepository.save(arrendador);
    }

    //Encuentra todos los elementos
    public List<Arrendador> ListarArrendadores()
    {
        return arrendadorRepository.findAll();
    }

    //Obtener por ID
    public Arrendador obtenerArrendadorPorId(Long id)
    {
        return arrendadorRepository.findById(id).orElse(null);
    }

    public Arrendador actualizarArrendador(@NonNull Long id, Arrendador arrendador)
    {
        if (arrendadorRepository.existsById(id))
        {
            Arrendador cambio = arrendadorRepository.findById(id).orElse(null);

            cambio.setNombre(arrendador.getNombre());
            cambio.setApellido(arrendador.getApellido());
            cambio.setCorreo(arrendador.getCorreo());
            cambio.setTelefono(arrendador.getTelefono());
            return arrendadorRepository.save(cambio);
        }

        return null;
    }

        public boolean deleteArrendador(Long id) {
            if (arrendadorRepository.existsById(id))
            {
                arrendadorRepository.deleteById(id);
                return true;
            }
            return false;
        }
    } 


