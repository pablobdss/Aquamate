package com.aquamate.Aquamate.service;

import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.MetaAuto;
import com.aquamate.Aquamate.model.MetaManual;
import com.aquamate.Aquamate.repository.DadosUsuarioRepository;
import com.aquamate.Aquamate.repository.MetaAutoRepository;
import com.aquamate.Aquamate.repository.MetaManualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {

    @Autowired
    private MetaAutoRepository metaAutoRepository;

    @Autowired
    private MetaManualRepository metaManualRepository;

    @Autowired
    private DadosUsuarioRepository dadosUsuarioRepository;
    public Optional<MetaAuto> getMetaAuto(Long id) {
        return metaAutoRepository.findById_dadosUsuario(id);
    }

    public Optional<MetaManual> getMetaManual(Long id) {
        return metaManualRepository.findById_dadosUsuario(id);
    }

    public MetaAuto saveMetaAuto(Long id_dadosUsuario, MetaAuto metaAuto) {
        DadosUsuario dadosUsuario = dadosUsuarioRepository.findById(id_dadosUsuario)
                .orElseThrow(() -> new IllegalArgumentException("DadosUsuario não encontrado com ID: " + id_dadosUsuario));
        dadosUsuario.setTipoMetaAutomatica();
        metaAuto.setDadosUsuario(dadosUsuario);
        return metaAutoRepository.save(metaAuto);
    }

    public MetaManual saveMetaManual(Long id_dadosUsuario, MetaManual metaManual) {
        DadosUsuario dadosUsuario = dadosUsuarioRepository.findById(id_dadosUsuario)
                .orElseThrow(() -> new IllegalArgumentException("DadosUsuario não encontrado com ID: " + id_dadosUsuario));
        dadosUsuario.setTipoMetaManual();
        metaManual.setDadosUsuario(dadosUsuario);
        return metaManualRepository.save(metaManual);
    }

    public Optional<MetaAuto> updateMetaAuto(Long id, MetaAuto metaAuto) {
        return metaAutoRepository.findById(id).map(existingMetaAuto -> {
            existingMetaAuto.setMetaAutomatica(metaAuto.getMetaAutomatica());
            DadosUsuario dadosUsuario = metaAuto.getDadosUsuario();
            if (dadosUsuario != null) {
                existingMetaAuto.setDadosUsuario(dadosUsuario);
                dadosUsuario.setTipoMetaAutomatica();
            }
            return metaAutoRepository.save(existingMetaAuto);
        });
    }

    public Optional<MetaManual> updateMetaManual(Long id, MetaManual metaManual) {
        return metaManualRepository.findById(id).map(existingMetaManual -> {
            existingMetaManual.setMetaManual(metaManual.getMetaManual());
            DadosUsuario dadosUsuario = metaManual.getDadosUsuario();
            if (dadosUsuario != null) {
                existingMetaManual.setDadosUsuario(dadosUsuario);
                dadosUsuario.setTipoMetaManual();
            }
            return metaManualRepository.save(existingMetaManual);
        });
    }


    public boolean deleteMetaAuto(Long id) {
        if (metaAutoRepository.existsById(id)) {
            metaAutoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteMetaManual(Long id) {
        if (metaManualRepository.existsById(id)) {
            metaManualRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<MetaManual> getAllMetaManuals() {
        return metaManualRepository.findAll();
    }
}
