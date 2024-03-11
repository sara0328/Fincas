package com.webdynamos.fincas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webdynamos.fincas.models.Calificacion_propiedad;
import com.webdynamos.fincas.services.CalificacionPropiedadService;

public interface CalificacionPropiedadRepository extends JpaRepository<Calificacion_propiedad, Long> {

    Calificacion_propiedad save(CalificacionPropiedadService calificacion);



}
