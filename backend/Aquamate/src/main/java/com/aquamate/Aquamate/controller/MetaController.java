package com.aquamate.Aquamate.controller;

import com.aquamate.Aquamate.model.MetaAuto;
import com.aquamate.Aquamate.model.MetaManual;
import com.aquamate.Aquamate.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/meta")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping("/all")
    public ResponseEntity<List<MetaManual>> getAllMetas() {
        List<MetaManual> metaManuals = metaService.getAllMetaManuals();
        return ResponseEntity.ok(metaManuals);
    }

    @GetMapping("/auto/{id_dadosUsuario}")
    public ResponseEntity<MetaAuto> getMetaAuto(@PathVariable Long id_dadosUsuario) {
        Optional<MetaAuto> metaAuto = metaService.getMetaAuto(id_dadosUsuario);
        return metaAuto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/manual/{id_dadosUsuario}")
    public ResponseEntity<MetaManual> getMetaManual(@PathVariable Long id_dadosUsuario) {
        Optional<MetaManual> metaManual = metaService.getMetaManual(id_dadosUsuario);
        return metaManual.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/auto/{id_dadosUsuario}")
    public ResponseEntity<MetaAuto> createMetaAuto(@PathVariable Long id_dadosUsuario, @RequestBody MetaAuto metaAuto) {
        MetaAuto createdMetaAuto = metaService.saveMetaAuto(id_dadosUsuario, metaAuto);
        return ResponseEntity.ok(createdMetaAuto);
    }

    @PostMapping("/manual/{id_dadosUsuario}")
    public ResponseEntity<MetaManual> createMetaManual(@PathVariable Long id_dadosUsuario, @RequestBody MetaManual metaManual) {
        MetaManual createdMetaManual = metaService.saveMetaManual(id_dadosUsuario, metaManual);
        return ResponseEntity.ok(createdMetaManual);
    }

    @PutMapping("/auto/{id_dadosUsuario}")
    public ResponseEntity<MetaAuto> updateMetaAuto(@PathVariable Long id_dadosUsuario) {
        Optional<MetaAuto> updatedMetaAuto = metaService.updateMetaAuto(id_dadosUsuario);
        return updatedMetaAuto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/manual/{id_dadosUsuario}")
    public ResponseEntity<MetaManual> updateMetaManual(@PathVariable Long id_dadosUsuario, @RequestBody MetaManual metaManual) {
        Optional<MetaManual> updatedMetaManual = metaService.updateMetaManual(id_dadosUsuario, metaManual);
        return updatedMetaManual.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/auto/{id_dadosUsuario}")
    public ResponseEntity<Void> deleteMetaAuto(@PathVariable Long id_dadosUsuario) {
        if (metaService.deleteMetaAuto(id_dadosUsuario)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/manual/{id_dadosUsuario}")
    public ResponseEntity<Void> deleteMetaManual(@PathVariable Long id_dadosUsuario) {
        if (metaService.deleteMetaManual(id_dadosUsuario)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


