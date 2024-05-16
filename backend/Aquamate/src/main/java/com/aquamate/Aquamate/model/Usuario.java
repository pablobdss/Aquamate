package com.aquamate.Aquamate.model;

import com.aquamate.Aquamate.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private DadosUsuario dadosUsuario;

    public Usuario(UsuarioDTO usuarioDTO) {
        this.email = email;
        this.senha = senha;
    }

}
