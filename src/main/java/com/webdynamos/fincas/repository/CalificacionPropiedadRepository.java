package com.webdynamos.fincas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webdynamos.fincas.models.CalificacionPropiedad;
import com.webdynamos.fincas.services.CalificacionPropiedadService;

public interface CalificacionPropiedadRepository extends JpaRepository<CalificacionPropiedad, Long> {

    CalificacionPropiedad save(CalificacionPropiedadService calificacion);



}
