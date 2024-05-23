package com.aquamate.Aquamate.controller;


import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.DadosUsuarioRepository;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import com.aquamate.Aquamate.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping ("/dadosUsuario")
public class DadosUsuarioController {

    @Autowired
    private DadosUsuarioRepository dadosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroService cadastroService;

    Usuario usuario = new Usuario();

    @GetMapping
    public ResponseEntity<DadosUsuario> obterDadosUsuario(@RequestParam Long id_usuario) {
        Optional<DadosUsuario> dadosUsuario = cadastroService.obterDadosUsuario(id_usuario);
        if (dadosUsuario.isPresent()) {
            return ResponseEntity.ok(dadosUsuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario")
    public ResponseEntity getAllUsers() {
        var allUsers = usuarioRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = cadastroService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @PostMapping("/post")
    public ResponseEntity<DadosUsuario> cadastrarDadosUsuario(@RequestParam Long id_usuario, @RequestBody DadosUsuario dadosUsuario) {
        DadosUsuario dadosUsuarioSalvo = cadastroService.cadastrarDadosUsuario(id_usuario, dadosUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosUsuarioSalvo);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<DadosUsuario> atualizarDadosUsuario(@RequestParam Long id_usuario, @RequestBody DadosUsuario dadosUsuario) {
        DadosUsuario dadosUsuarioAtualizado = cadastroService.atualizarDadosUsuario(id_usuario, dadosUsuario);
        return ResponseEntity.ok(dadosUsuarioAtualizado);
    }

}
