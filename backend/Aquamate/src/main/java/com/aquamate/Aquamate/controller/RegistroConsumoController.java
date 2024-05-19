package com.aquamate.Aquamate.controller;

import com.aquamate.Aquamate.dto.RegistroConsumoDTO;
import com.aquamate.Aquamate.model.RegistroConsumo;
import com.aquamate.Aquamate.service.RegistroConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/consumo")
public class RegistroConsumoController {

    @Autowired
    private RegistroConsumoService registroConsumoService;

    @PostMapping("/registrar")
    public ResponseEntity<RegistroConsumo> registrarConsumo(@RequestParam Long id_dadosUsuario, RegistroConsumoDTO registroConsumoDTO) {
        RegistroConsumo registroConsumoSalvo = registroConsumoService.registrarConsumo(id_dadosUsuario, registroConsumoDTO);
        return ResponseEntity.ok(registroConsumoSalvo);
    }

    @GetMapping("/resgatar")
    public ResponseEntity<Optional<RegistroConsumo>> resgatarConsumo(@RequestParam Long id_dadosUsuario) {
        Optional<RegistroConsumo> registroConsumo = registroConsumoService.getRegistroConsumoPorID(id_dadosUsuario);
        return ResponseEntity.ok(registroConsumo);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<RegistroConsumo> atualizarConsumo(@RequestParam Long id_dadosUsuario, @RequestBody RegistroConsumoDTO registroConsumoDTO) {
        RegistroConsumo registroConsumoAtualizado = registroConsumoService.atualizarConsumoPorIdDadosUsuario(id_dadosUsuario, registroConsumoDTO);
        return ResponseEntity.ok(registroConsumoAtualizado);
    }
}
