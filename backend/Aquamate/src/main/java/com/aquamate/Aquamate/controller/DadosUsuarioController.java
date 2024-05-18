package com.aquamate.Aquamate.controller;

import com.aquamate.Aquamate.dto.DadosUsuarioDTO;
import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.DadosUsuarioRepository;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import com.aquamate.Aquamate.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/{id_usuario}")
    public ResponseEntity<DadosUsuario> getDadosByUsuarioId(@PathVariable Long id_usuario) {
        Optional<DadosUsuario> dados = cadastroService.getDadosByUsuarioId(id_usuario);
        return dados.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

    @PostMapping("/{id_usuario}/post")
    public ResponseEntity<DadosUsuario> cadastrarDadosUsuario(@PathVariable Long id_usuario, @RequestBody DadosUsuario dadosUsuario) {
        DadosUsuario dadosUsuarioSalvo = cadastroService.cadastrarDadosUsuario(id_usuario, dadosUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosUsuarioSalvo);
    }

    @PostMapping
    public ResponseEntity postDados(@RequestBody @Validated DadosUsuarioDTO data) {
        DadosUsuario dadosUsuario = new DadosUsuario(data);
        System.out.println(data);
        dadosRepository.save(dadosUsuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id_usuario}")
    public ResponseEntity updateDados(@RequestBody @Validated DadosUsuarioDTO data) {
        Optional<DadosUsuario> dadosUsuarioOptional = dadosRepository.findById(data.id());
        var usuario = new Usuario();

        if (dadosUsuarioOptional.isPresent()) {
            DadosUsuario dadosUsuario = dadosUsuarioOptional.get();

            // Atualize os campos do dadosUsuario com base nos dados recebidos no DTO
            dadosUsuario.setApelido(data.apelido());
            dadosUsuario.setPeso(data.peso());
            dadosUsuario.setIdade(data.idade());
            dadosUsuario.setAltura(data.altura());
            dadosUsuario.setTipoMeta(data.tipoMeta());
            dadosUsuario.setDataNascimento(data.dataNascimento());

            // Salve as alterações no repositório
            dadosRepository.save(dadosUsuario);

            return ResponseEntity.ok(dadosUsuario);
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 caso o DadosUsuario não seja encontrado
        }
    }

}
