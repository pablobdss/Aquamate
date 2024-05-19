package com.aquamate.Aquamate.model;
import com.aquamate.Aquamate.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 20)
    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private DadosUsuario dadosUsuario;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public DadosUsuario getDadosUsuario() {
        return dadosUsuario;
    }

    public void setDadosUsuario(DadosUsuario dadosUsuario) {
        dadosUsuario.setUsuario(this);
        this.dadosUsuario = dadosUsuario;
    }
}