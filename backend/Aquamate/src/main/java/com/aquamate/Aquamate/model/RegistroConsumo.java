package com.aquamate.Aquamate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "Registro de Consumo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RegistroConsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataRegistro;

    private Integer quantidadeConsumida;

    private Integer percentualAtingido;

    private Integer streak;

    @OneToOne
    @JoinColumn(name = "id_dadosUsuario")
    private DadosUsuario dadosUsuario;
}
