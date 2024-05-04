package com.aquamate.Aquamate.principal;

import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.UsuarioRepository;

public class Principal {
    private final UsuarioRepository repository;

    public Principal(UsuarioRepository repository) {this.repository = repository;}

    public void TestePrincipal() {
        System.out.println("Hello World");
        Usuario usuario = new Usuario ();

        repository.save(usuario);
    }
}
