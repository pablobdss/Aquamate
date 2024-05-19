package com.aquamate.Aquamate.repository;

import com.aquamate.Aquamate.model.MetaManual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MetaManualRepository extends JpaRepository<MetaManual, Long> {
    @Query("SELECT mm FROM MetaManual mm WHERE mm.dadosUsuario.id = :id_dadosUsuario")
    Optional<MetaManual> findById_dadosUsuario(Long id_dadosUsuario);
}
