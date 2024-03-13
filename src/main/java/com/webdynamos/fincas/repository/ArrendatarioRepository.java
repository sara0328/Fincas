package com.webdynamos.fincas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webdynamos.fincas.models.Arrendatario;

public interface ArrendatarioRepository extends JpaRepository<Arrendatario, Long> {
}
