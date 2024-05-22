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

    public RegistroConsumo atualizarConsumoPorIdDadosUsuario(Long id_dadosUsuario, RegistroConsumoDTO novoConsumo) {
        Optional<RegistroConsumo> registroConsumoAnteriorOpt = registroConsumoRepository.findByDadosUsuarioId(id_dadosUsuario);
        RegistroConsumo registroConsumoAnterior = registroConsumoAnteriorOpt.orElse(new RegistroConsumo()); // Criar novo registro se não existir

        int novaQuantidadeConsumida = registroConsumoAnterior.getQuantidadeConsumida() + novoConsumo.quantidadeConsumida();
        registroConsumoAnterior.setQuantidadeConsumida(novaQuantidadeConsumida);

        // Obtém a meta atualizada para o usuário
        int metaAtual = obterMetaAtual(id_dadosUsuario);

        // Calcula o percentual atingido com base na meta atualizada
        int percentualAtingido = calcularPercentualAtingido(novaQuantidadeConsumida, metaAtual);

        // Define o percentual atingido no registro de consumo
        registroConsumoAnterior.setPercentualAtingido(percentualAtingido);

        // Salva e retorna o registro de consumo atualizado
        return registroConsumoRepository.save(registroConsumoAnterior);
    }

    public void atualizarPercentualAoAlterarTipoMeta(Long id_dadosUsuario) {
        Optional<DadosUsuario> dadosUsuarioOpt = dadosUsuarioRepository.findById(id_dadosUsuario);
        if (dadosUsuarioOpt.isPresent()) {
            DadosUsuario dadosUsuario = dadosUsuarioOpt.get();
            boolean tipoMeta = dadosUsuario.getTipoMeta();
            Long id_usuario = dadosUsuario.getUsuario().getId(); // Para encontrar os dados do usuário

            // Obtém a meta atualizada para o usuário
            int metaAtual = obterMetaAtual(id_dadosUsuario);

            // Obtém o registro de consumo atual para o usuário
            Optional<RegistroConsumo> registroConsumoOpt = registroConsumoRepository.findByDadosUsuarioId(id_usuario);

            if (registroConsumoOpt.isPresent()) {
                RegistroConsumo registroConsumo = registroConsumoOpt.get();

                // Atualiza o percentual atingido com base na meta atualizada
                int novoPercentualAtingido = calcularPercentualAtingido(registroConsumo.getQuantidadeConsumida(), metaAtual);

                // Define o novo percentual atingido no registro de consumo
                registroConsumo.setPercentualAtingido(novoPercentualAtingido);

                // Salva o registro de consumo atualizado
                registroConsumoRepository.save(registroConsumo);
            }
        }
    }

    private int obterMetaAtual(Long id_dadosUsuario) {
        Optional<DadosUsuario> dadosUsuarioOpt = dadosUsuarioRepository.findById(id_dadosUsuario);
        if (dadosUsuarioOpt.isPresent()) {
            boolean tipoMeta = dadosUsuarioOpt.get().getTipoMeta();
            if (tipoMeta) {
                // Se tipoMeta for verdadeiro, utiliza a meta automática
                return metaAutoRepository.findById_dadosUsuario(id_dadosUsuario)
                        .map(MetaAuto::getMetaAutomatica)
                        .orElse(calcularMetaAutomatica(id_dadosUsuario));
            } else {
                // Caso contrário, utiliza a meta manual
                return metaManualRepository.findById_dadosUsuario(id_dadosUsuario)
                        .map(MetaManual::getMetaManual)
                        .orElse(0);
            }
        } else {
            // Se os dados do usuário não forem encontrados, utiliza uma meta padrão
            return calcularMetaAutomatica(id_dadosUsuario);
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


