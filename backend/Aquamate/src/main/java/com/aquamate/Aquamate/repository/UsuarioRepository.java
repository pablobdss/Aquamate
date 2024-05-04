package com.aquamate.Aquamate.repository;

import com.aquamate.Aquamate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
