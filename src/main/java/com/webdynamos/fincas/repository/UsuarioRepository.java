package com.webdynamos.fincas.repository;

import com.webdynamos.fincas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
