package com.aquamate.Aquamate.model;

import com.aquamate.Aquamate.dto.DadosUsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "dados_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DadosUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataNascimento;

    private String apelido;

    private Double peso;

    private Integer idade;

    private Integer altura;

    private Boolean tipoMeta;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToOne(mappedBy = "dadosUsuario", cascade = CascadeType.ALL)
    private RegistroConsumo registroConsumo;

    @OneToOne(mappedBy = "dadosUsuario", cascade = CascadeType.ALL)
    private MetaAuto metaAuto;

    @OneToOne(mappedBy = "dadosUsuario", cascade = CascadeType.ALL)
    private MetaManual metaManual;

    public DadosUsuario(DadosUsuarioDTO dadosUsuarioDTO) {
        this.apelido = dadosUsuarioDTO.apelido();
        this.peso = dadosUsuarioDTO.peso();
        this.idade = dadosUsuarioDTO.idade();
        this.altura = dadosUsuarioDTO.altura();
        this.tipoMeta = dadosUsuarioDTO.tipoMeta();
        this.dataNascimento = dadosUsuarioDTO.dataNascimento();
    }

    public void setTipoMetaManual() {
        this.tipoMeta = false;
    }

    public void setTipoMetaAutomatica() {
        this.tipoMeta = true;
    }
}

