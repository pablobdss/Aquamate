package com.aquamate.Aquamate.repository;

import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DadosUsuarioRepository extends JpaRepository<DadosUsuario, Long> {

    @Query("SELECT d FROM DadosUsuario d WHERE d.usuario.id = :id_usuario")
    Optional<DadosUsuario> findByIdUsuario(@Param("id_usuario") Long id_usuario);
}

