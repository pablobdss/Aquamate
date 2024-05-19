package com.aquamate.Aquamate.repository;

import com.aquamate.Aquamate.model.MetaAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MetaAutoRepository extends JpaRepository<MetaAuto, Long> {
    @Query("SELECT ma FROM MetaAuto ma WHERE ma.dadosUsuario.id = :id_dadosUsuario")
    Optional<MetaAuto> findById_dadosUsuario(Long id_dadosUsuario);
}
