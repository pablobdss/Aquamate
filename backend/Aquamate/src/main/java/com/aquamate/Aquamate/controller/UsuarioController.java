package com.aquamate.Aquamate.controller;



import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.AuthenticationException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarUsuario(@Validated @RequestBody UsuarioDTO dados) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dados.email());
        usuario.setSenha(dados.senha());
        usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> fazerLogin(@RequestBody @Validated UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.fazerLogin(usuarioDTO.email(), usuarioDTO.senha());
            return ResponseEntity.ok(usuario);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

}