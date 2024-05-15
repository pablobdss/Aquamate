package com.aquamate.Aquamate.service;

import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    UsuarioDTO converterParaDTO(Usuario usuario) {
        return null;
    }

    Usuario converterParaEntidade(UsuarioDTO usuarioDTO) {
        return null;
    }

    private  List<UsuarioDTO> registrarUsuario(Usuario usuario) {
        return (List<UsuarioDTO>) (List<UsuarioDTO>) usuario.stream()
                .map(s -> new UsuarioDTO(s.getEmail(), s.getSenha()))
                .collect(Collectors.toList());
    }
}
