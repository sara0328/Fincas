package com.webdynamos.fincas.repository;
import com.webdynamos.fincas.models.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository <Solicitud, Long> {
}
