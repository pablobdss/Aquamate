package com.aquamate.Aquamate.controller;


import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import com.aquamate.Aquamate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    Usuario usuario = new Usuario();

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
            // Realiza o login e retorna o usuário (sem a senha) caso seja bem-sucedido
            Usuario usuario = usuarioService.fazerLogin(usuarioDTO.email(), usuarioDTO.senha());
            usuario.setSenha(null); // Remove a senha antes de enviar como resposta
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(UNAUTHORIZED).body("Credenciais inválidas");
        }
    }

}
