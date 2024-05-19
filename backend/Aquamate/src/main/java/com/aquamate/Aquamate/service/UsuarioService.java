package com.aquamate.Aquamate.service;

import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);

    }

    public Usuario fazerLogin(String email, String senha) {

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        throw new RuntimeException("Credenciais inválidas");
    }
}
