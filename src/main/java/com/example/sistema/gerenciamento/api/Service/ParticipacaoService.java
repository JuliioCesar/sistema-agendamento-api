package com.example.sistema.gerenciamento.api.Service;

import com.example.sistema.gerenciamento.api.Dto.AtualizarStatusRequest;
import com.example.sistema.gerenciamento.api.Dto.ParticipacaoRequest;
import com.example.sistema.gerenciamento.api.Dto.ParticipacaoResponse;
import com.example.sistema.gerenciamento.api.Entity.HistoricoStatus;
import com.example.sistema.gerenciamento.api.Entity.Participacao;
import com.example.sistema.gerenciamento.api.Entity.Pessoa;
import com.example.sistema.gerenciamento.api.Entity.Servico;
import com.example.sistema.gerenciamento.api.Entity.StatusAtividade;
import com.example.sistema.gerenciamento.api.Exception.ResourceNotFoundException;
import com.example.sistema.gerenciamento.api.Repository.HistoricoStatusRepository;
import com.example.sistema.gerenciamento.api.Repository.ParticipacaoRepository;
import com.example.sistema.gerenciamento.api.Repository.PessoaRepository;
import com.example.sistema.gerenciamento.api.Repository.ServicoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParticipacaoService {

    private final ParticipacaoRepository participacaoRepository;
    private final PessoaRepository pessoaRepository;
    private final ServicoRepository servicoRepository;
    private final HistoricoStatusRepository historicoRepository;

    // [ GET ] - RETORNA TODAS AS PARTICIPAÇÕES
    @Transactional(readOnly = true)
    public List<ParticipacaoResponse> listarTodas() {
        return participacaoRepository.findAll().stream()
                .map(ParticipacaoResponse::fromEntity)
                .toList();
    }

    // [ GET ] - BUSCA PARTICIPAÇÃO POR ID
    @Transactional(readOnly = true)
    public ParticipacaoResponse buscarPorId(Long id) {
        Participacao participacao = participacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participação não encontrada com o ID: " + id));
        return ParticipacaoResponse.fromEntity(participacao);
    }

    // [ GET ] - LISTA PARTICIPAÇÕES POR PESSOA
    @Transactional(readOnly = true)
    public List<ParticipacaoResponse> listarPorPessoa(Long pessoaId) {
        return participacaoRepository.findByPessoaId(pessoaId).stream()
                .map(ParticipacaoResponse::fromEntity)
                .toList();
    }

    // [ GET ] - LISTA PARTICIPAÇÕES POR SERVIÇO
    @Transactional(readOnly = true)
    public List<ParticipacaoResponse> listarPorServico(Long servicoId) {
        return participacaoRepository.findByServicoId(servicoId).stream()
                .map(ParticipacaoResponse::fromEntity)
                .toList();
    }

    // [ POST ] - CADASTRA UMA NOVA PARTICIPAÇÃO E REGISTRA O HISTÓRICO INICIAL
    @Transactional
    public ParticipacaoResponse salvar(ParticipacaoRequest dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + dto.pessoaId()));

        Servico servico = servicoRepository.findById(dto.servicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + dto.servicoId()));

        if (participacaoRepository.existsByPessoaIdAndServicoIdAndDataHora(dto.pessoaId(), dto.servicoId(), dto.dataHora())) {
            throw new IllegalArgumentException("Já existe um agendamento para esta pessoa neste serviço na mesma data e hora.");
        }

        StatusAtividade statusInicial = dto.status() != null ? dto.status() : StatusAtividade.AGENDADA;

        Participacao participacao = Participacao.builder()
                .dataHora(dto.dataHora())
                .status(statusInicial)
                .pessoa(pessoa)
                .servico(servico)
                .build();

        Participacao salva = participacaoRepository.save(participacao);
        registrarHistorico(salva, salva.getStatus(), "Agendamento criado");

        return ParticipacaoResponse.fromEntity(salva);
    }

    // [ PUT ] - ATUALIZA O STATUS DA PARTICIPAÇÃO E REGISTRA NO HISTÓRICO
    @Transactional
    public ParticipacaoResponse atualizarStatus(Long id, AtualizarStatusRequest request) {
        Participacao participacao = participacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participação não encontrada com o ID: " + id));

        participacao.setStatus(request.status());
        Participacao atualizada = participacaoRepository.save(participacao);
        registrarHistorico(atualizada, request.status(), request.observacao());

        return ParticipacaoResponse.fromEntity(atualizada);
    }

    // [ DELETE ] - EXCLUI A PARTICIPAÇÃO POR ID
    @Transactional
    public void excluir(Long id) {
        if (!participacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Participação não encontrada com o ID: " + id);
        }
        participacaoRepository.deleteById(id);
    }

    // REGISTRA AUTOMATICAMENTE O HISTÓRICO DE STATUS
    private void registrarHistorico(Participacao participacao, StatusAtividade status, String observacao) {
        HistoricoStatus historico = HistoricoStatus.builder()
                .status(status)
                .observacao(observacao)
                .participacao(participacao)
                .build();

        historicoRepository.save(historico);
    }
}