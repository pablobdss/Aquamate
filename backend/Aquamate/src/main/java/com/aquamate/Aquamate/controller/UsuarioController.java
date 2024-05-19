package com.aquamate.Aquamate.controller;


import com.aquamate.Aquamate.dto.DadosUsuarioDTO;
import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import com.aquamate.Aquamate.service.IConverteDados;
import com.aquamate.Aquamate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private IConverteDados converteDadosService;

    Usuario usuario = new Usuario();

    @Autowired
    public UsuarioController(UsuarioService usuarioService, IConverteDados converteDadosService) {
        this.usuarioService = usuarioService;
        this.converteDadosService = converteDadosService;
    }

    @PostMapping("/registro")
    public UsuarioDTO registrarUsuario(@RequestBody @Validated  UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario(usuarioDTO);
        System.out.println(usuarioDTO);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public UsuarioDTO fazerLogin(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.fazerLogin(usuarioDTO.email(), usuarioDTO.senha());
        return converteDadosService.converterParaDTO(usuario);
    }

}
