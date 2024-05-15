package com.aquamate.Aquamate.controller;


import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.service.IConverteDados;
import com.aquamate.Aquamate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final IConverteDados converteDadosService;
    private UsuarioDTO usuarioDTO;

    @Autowired
    private UsuarioService servico;

    public UsuarioController(UsuarioService usuarioService, IConverteDados converteDadosService, UsuarioService servico) {
        this.usuarioService = usuarioService;
        this.converteDadosService = converteDadosService;
        this.servico = servico;
    }

    @PostMapping("/registro")
    public Usuario registrarUsuario(@Request UsuarioDTO usuarioDTO) {
        Usuario usuario = converteDadosService.converterParaEntidade(usuarioDTO);
        return converteDadosService.converterParaEntidade(usuarioService.registrarUsuario(usuario));
    }

    @PostMapping("/login")
    public UsuarioDTO realizarLogin(@Request UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.fazerLogin(usuarioDTO.getEmail(), usuarioDTO.getSenha());
        return converteDadosService.converterParaDTO(usuario);
    }
}
