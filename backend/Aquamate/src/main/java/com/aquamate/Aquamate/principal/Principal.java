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
        Usuario usuario = new Usuario ();
        usuario.setEmail("teste@gmail.com223233d");
        userRepository.save(usuario);
        System.out.println("Cheguei 1");

        DadosUsuario dados = new DadosUsuario();
        dados.setApelido("Joao");
        dadosUsuarioRepository.save(dados);
        System.out.println("Cheguei 2");
    }

}
