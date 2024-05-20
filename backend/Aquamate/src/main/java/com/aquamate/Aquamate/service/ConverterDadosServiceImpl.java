//package com.aquamate.Aquamate.service;
//
//import com.aquamate.Aquamate.dto.UsuarioDTO;
//import com.aquamate.Aquamate.model.Usuario;
//
//public class ConverterDadosServiceImpl implements IConverteDados {
//
//    @Override
//    public UsuarioDTO converterParaDTO(Usuario usuario) {
//        return new UsuarioDTO(usuario.getEmail(), usuario.getSenha());
//    }
//
//    @Override
//    public <T> T obterDados(String json, Class<T> classe) {
//        return null;
//    }
//
//    @Override
//    public Usuario converterParaEntidade(UsuarioDTO usuarioDTO) {
//        return new Usuario(usuarioDTO.email(), usuarioDTO.senha());
//    }
//}
