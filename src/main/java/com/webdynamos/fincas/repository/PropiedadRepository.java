package com.webdynamos.fincas.repository;
import com.webdynamos.fincas.models.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
    List<Propiedad> findByArrendatarioId(Long arrendatarioId);
    List<Propiedad> findByArrendadorId(Long arrendadorId);
}