package com.aquamate.Aquamate.principal;

import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.UsuarioRepository;


public class Principal {
    private final UsuarioRepository userRepository;

    public Principal(UsuarioRepository repository) {
        this.userRepository = repository;
    }

    public void testePrincipal() {
        System.out.println("Hello World");
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@gmailw2w.com");
        usuario.setSenha("senha123");

        // Criar um novo DadosUsuario e associá-lo ao Usuario
        DadosUsuario dadosUsuario = new DadosUsuario();
        dadosUsuario.setApelido("Joaozin");
        usuario.setDadosUsuario(dadosUsuario);

        // Salvar o Usuario
        userRepository.save(usuario);

        System.out.println(dadosUsuario);
        System.out.println(usuario);
    }

}
