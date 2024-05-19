package com.aquamate.Aquamate.service;

import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);

    Usuario converterParaEntidade(UsuarioDTO usuarioDTO);

    UsuarioDTO converterParaDTO(Usuario usuario);
}
