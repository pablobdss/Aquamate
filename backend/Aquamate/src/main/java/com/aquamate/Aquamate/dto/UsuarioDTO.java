package com.aquamate.Aquamate.dto;


import com.aquamate.Aquamate.model.Usuario;

public record UsuarioDTO (String email,
                          String senha){

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}

