package com.aquamate.Aquamate.repository;

import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.RegistroConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RegistroConsumoRepository extends JpaRepository<RegistroConsumo, Long> {
    Optional<RegistroConsumo> findByDadosUsuarioId(Long id_dadosUsuario);
}
