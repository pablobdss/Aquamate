package com.aquamate.Aquamate.service;

import com.aquamate.Aquamate.dto.RegistroConsumoDTO;
import com.aquamate.Aquamate.model.DadosUsuario;
import com.aquamate.Aquamate.model.MetaAuto;
import com.aquamate.Aquamate.model.MetaManual;
import com.aquamate.Aquamate.model.RegistroConsumo;
import com.aquamate.Aquamate.repository.DadosUsuarioRepository;
import com.aquamate.Aquamate.repository.MetaAutoRepository;
import com.aquamate.Aquamate.repository.MetaManualRepository;
import com.aquamate.Aquamate.repository.RegistroConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroConsumoService {

    @Autowired
    private RegistroConsumoRepository registroConsumoRepository;

    @Autowired
    private DadosUsuarioRepository dadosUsuarioRepository;

    @Autowired
    private MetaManualRepository metaManualRepository;

    @Autowired
    private MetaAutoRepository metaAutoRepository;

    public RegistroConsumo registrarConsumo(Long id_dadosUsuario, RegistroConsumoDTO registroConsumoDTO) {
        DadosUsuario dadosUsuario = dadosUsuarioRepository.findById(id_dadosUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Dados do usuário não encontrados com o ID: " + id_dadosUsuario));

        Optional<MetaManual> metaManualOpt = obterMetaManualPorUsuario(dadosUsuario);
        Optional<MetaAuto> metaAutoOpt = obterMetaAutoPorUsuario(dadosUsuario);

        Integer meta = metaManualOpt.map(MetaManual::getMetaManual)
                .orElseGet(() -> metaAutoOpt.map(MetaAuto::getMetaAutomatica)
                        .orElse((int) (35 * dadosUsuario.getPeso())));

        RegistroConsumo registroConsumo = new RegistroConsumo();
        registroConsumo.setDadosUsuario(dadosUsuario);
        registroConsumo.setQuantidadeConsumida(registroConsumoDTO.quantidadeConsumida());
        registroConsumo.setPercentualAtingido(calcularPercentualAtingido(registroConsumoDTO.quantidadeConsumida(), meta));

        return registroConsumoRepository.save(registroConsumo);
    }

    public RegistroConsumo atualizarConsumoPorIdDadosUsuario(Long id_dadosUsuario, RegistroConsumo novoConsumo) {
        Optional<RegistroConsumo> registroConsumoAnteriorOpt = registroConsumoRepository.findByDadosUsuarioId(id_dadosUsuario);
        RegistroConsumo registroConsumoAnterior = registroConsumoAnteriorOpt.orElse(new RegistroConsumo()); // Criar novo registro se não existir

        int novaQuantidadeConsumida = registroConsumoAnterior.getQuantidadeConsumida() + novoConsumo.getQuantidadeConsumida();
        registroConsumoAnterior.setQuantidadeConsumida(novaQuantidadeConsumida);
        registroConsumoAnterior.setPercentualAtingido(calcularPercentualAtingido(novaQuantidadeConsumida, obterMetaParaUsuario(id_dadosUsuario)));

        return registroConsumoRepository.save(registroConsumoAnterior);
    }

    private int obterMetaParaUsuario(Long id_dadosUsuario) {
        Optional<MetaManual> metaManualOpt = metaManualRepository.findById_dadosUsuario(id_dadosUsuario);
        if (metaManualOpt.isPresent()) {
            return metaManualOpt.get().getMetaManual();
        } else {
            Optional<MetaAuto> metaAutoOpt = metaAutoRepository.findById_dadosUsuario(id_dadosUsuario);
            return metaAutoOpt.map(MetaAuto::getMetaAutomatica).orElseGet(() -> calcularMetaAutomatica(id_dadosUsuario));
        }
    }

    private int calcularMetaAutomatica(Long id_dadosUsuario) {
        Optional<DadosUsuario> dadosUsuarioOpt = dadosUsuarioRepository.findById(id_dadosUsuario);
        return dadosUsuarioOpt.map(dadosUsuario -> (int) (dadosUsuario.getPeso() * 35)).orElse(0);
    }

    private Integer calcularPercentualAtingido(Integer quantidadeConsumida, Integer meta) {
        return (quantidadeConsumida * 100) / meta;
    }

    private Optional<MetaManual> obterMetaManualPorUsuario(DadosUsuario dadosUsuario) {
        return metaManualRepository.findById_dadosUsuario(dadosUsuario.getId());
    }

    private Optional<MetaAuto> obterMetaAutoPorUsuario(DadosUsuario dadosUsuario) {
        return metaAutoRepository.findById_dadosUsuario(dadosUsuario.getId());
    }

    public Optional<RegistroConsumo> getRegistroConsumoPorID(Long id_usuario) {
        return registroConsumoRepository.findByDadosUsuarioId(id_usuario);
    }

    public Object findAll() {
        return null;
    }
}


