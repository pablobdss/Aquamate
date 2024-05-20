package com.aquamate.Aquamate.repository;

import com.aquamate.Aquamate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailAndSenha(String email, String senha);
    Optional<Usuario> findByEmail(String email);
}