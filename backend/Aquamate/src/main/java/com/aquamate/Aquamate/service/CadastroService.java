package com.aquamate.Aquamate.service;

import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.DadosUsuarioRepository;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CadastroService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DadosUsuarioRepository dadosUsuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public DadosUsuario cadastrarDadosUsuario(Long idUsuario, DadosUsuario dadosUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + idUsuario));
        dadosUsuario.setUsuario(usuario);
        return dadosUsuarioRepository.save(dadosUsuario);
    }

    public Optional<DadosUsuario> getDadosByUsuarioId(Long idUsuario) {
        return dadosUsuarioRepository.findByIdUsuario(idUsuario);
    }
}

