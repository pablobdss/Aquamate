package com.aquamate.Aquamate.dto;

import com.aquamate.Aquamate.model.DadosUsuario;

public record RegistroConsumoDTO(
        Long id,
        Integer quantidadeConsumida,
        Integer percentualAtingido,
        Long id_dadosUsuario
) {
}
