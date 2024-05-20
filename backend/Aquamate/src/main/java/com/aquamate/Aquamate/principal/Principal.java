package com.aquamate.Aquamate.principal;

import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.DadosUsuarioRepository;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class Principal {
    private final UsuarioRepository userRepository;
    private final DadosUsuarioRepository dadosUsuarioRepository;

    public Principal(UsuarioRepository repository, DadosUsuarioRepository dadosUsuarioRepository) {
        this.userRepository = repository;
        this.dadosUsuarioRepository = dadosUsuarioRepository;
    }

    public void testePrincipal() {
        System.out.println("Hello World");
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@gmail22222.com");
        usuario.setSenha("senha123");

        // Criar um novo DadosUsuario e associá-lo ao Usuario
        DadosUsuario dadosUsuario = new DadosUsuario();
        dadosUsuario.setApelido("Joaozin");
        usuario.setDadosUsuario(dadosUsuario);

        // Salvar o Usuario
        userRepository.save(usuario);

        System.out.println("Cheguei");
    }

}
