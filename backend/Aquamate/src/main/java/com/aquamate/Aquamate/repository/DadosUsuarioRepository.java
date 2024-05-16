package com.aquamate.Aquamate.repository;

import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DadosUsuarioRepository extends JpaRepository<DadosUsuario, Long> {

//    List<DadosUsuario> findById_Usuario(Long id);
}
