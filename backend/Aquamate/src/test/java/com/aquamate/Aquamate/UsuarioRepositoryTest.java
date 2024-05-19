package com.aquamate.Aquamate;

import com.aquamate.Aquamate.model.Usuario;
import com.aquamate.Aquamate.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCriarUsuario() {
        Usuario usuario = new Usuario();

        usuario.setEmail("teste@gmail.com");
        usuario.setSenha("password");

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        Usuario usuarioExistente= entityManager.find(Usuario.class, usuarioSalvo.getId());

        assertThat(usuarioExistente.getEmail()).isEqualTo(usuario.getEmail());
    }

}
