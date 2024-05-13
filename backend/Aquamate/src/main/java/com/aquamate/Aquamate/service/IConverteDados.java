package com.aquamate.Aquamate.service;

import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.Usuario;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
    UsuarioDTO converterParaDTO(Usuario usuario);
    Usuario converterParaEntidade(UsuarioDTO usuarioDTO);
}
