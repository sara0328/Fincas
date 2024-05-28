package com.webdynamos.fincas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webdynamos.fincas.models.Arrendador;

import java.util.Optional;

public interface ArrendadorRepository extends JpaRepository<Arrendador, Long> {
    Optional<Arrendador> findByUsername(String username);
}
