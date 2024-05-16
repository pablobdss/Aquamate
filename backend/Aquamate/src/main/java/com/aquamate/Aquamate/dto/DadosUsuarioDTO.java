package com.aquamate.Aquamate.dto;

import com.aquamate.Aquamate.model.Usuario;

import java.time.LocalDate;

public record DadosUsuarioDTO (
        Long id,
        String apelido,
        Double peso,
        Integer idade,
        Integer altura,
        Boolean tipoMeta,
        LocalDate dataNascimento,
        Usuario id_usuario){
}
