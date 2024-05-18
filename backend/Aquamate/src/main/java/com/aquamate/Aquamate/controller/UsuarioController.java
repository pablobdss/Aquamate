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
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final IConverteDados converteDadosService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, IConverteDados converteDadosService) {
        this.usuarioService = usuarioService;
        this.converteDadosService = converteDadosService;
    }

    @PostMapping("/registro")
    public UsuarioDTO registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = converteDadosService.converterParaEntidade(usuarioDTO);
        Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);
        return converteDadosService.converterParaDTO(usuarioRegistrado);
    }

    @PostMapping("/login")
    public UsuarioDTO fazerLogin(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.fazerLogin(usuarioDTO.email(), usuarioDTO.senha());
        return converteDadosService.converterParaDTO(usuario);
    }


}
