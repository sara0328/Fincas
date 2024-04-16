package com.webdynamos.fincas.repository;

import com.webdynamos.fincas.models.CalificacionPropiedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionPropiedadRepository extends JpaRepository<CalificacionPropiedad, Long> {
}
