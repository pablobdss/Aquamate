package com.aquamate.Aquamate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Meta_Manual")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties("dadosUsuario")
public class MetaManual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer metaManual;

    @OneToOne
    @JoinColumn(name = "id_dadosUsuario")
    private DadosUsuario dadosUsuario;
}
