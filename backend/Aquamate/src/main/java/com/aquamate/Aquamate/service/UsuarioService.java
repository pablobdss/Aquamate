package com.aquamate.Aquamate.service;

import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (usuario.getSenha().equals(senha)) {
                return usuario;
            } else {
                throw new RuntimeException("Senha inválida");
            }
        } else throw new RuntimeException("Usuário não encontrado");
    }

}
