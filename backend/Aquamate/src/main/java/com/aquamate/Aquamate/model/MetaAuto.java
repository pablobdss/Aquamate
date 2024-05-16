package com.aquamate.Aquamate.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Meta_Automatica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MetaAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer metaAutomatica;

    @OneToOne
    @JoinColumn(name = "id_dadosUsuario")
    private DadosUsuario dadosUsuario;
}
