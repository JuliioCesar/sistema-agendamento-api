package com.example.sistema.gerenciamento.api.Service;

import com.example.sistema.gerenciamento.api.Dto.HistoricoStatusRequest;
import com.example.sistema.gerenciamento.api.Dto.HistoricoStatusResponse;
import com.example.sistema.gerenciamento.api.Entity.HistoricoStatus;
import com.example.sistema.gerenciamento.api.Entity.Participacao;
import com.example.sistema.gerenciamento.api.Entity.Usuario;
import com.example.sistema.gerenciamento.api.Exception.ResourceNotFoundException;
import com.example.sistema.gerenciamento.api.Repository.HistoricoStatusRepository;
import com.example.sistema.gerenciamento.api.Repository.ParticipacaoRepository;
import com.example.sistema.gerenciamento.api.Repository.UsuarioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HistoricoStatusService {

    private final HistoricoStatusRepository historicoStatusRepository;
    private final ParticipacaoRepository participacaoRepository;
    private final UsuarioRepository usuarioRepository;

    // [ GET ] - RETORNA TODOS OS REGISTROS DE HISTÓRICO DE STATUS
    @Transactional(readOnly = true)
    public List<HistoricoStatusResponse> listarTodos() {
        return historicoStatusRepository.findAll().stream()
                .map(HistoricoStatusResponse::fromEntity)
                .toList();
    }

    // [ GET ] - BUSCA HISTÓRICO PELO ID
    @Transactional(readOnly = true)
    public HistoricoStatusResponse buscarPorId(Long id) {
        HistoricoStatus historico = historicoStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de histórico não encontrado com o ID: " + id));
        return HistoricoStatusResponse.fromEntity(historico);
    }

    // [ GET ] - RETORNA O HISTÓRICO DE UMA PARTICIPAÇÃO
    @Transactional(readOnly = true)
    public List<HistoricoStatusResponse> listarPorParticipacao(Long participacaoId) {
        return historicoStatusRepository.findByParticipacaoIdOrderByDataAlteracaoDesc(participacaoId).stream()
                .map(HistoricoStatusResponse::fromEntity)
                .toList();
    }

    // [ POST ] - REGISTRA UMA NOVA ALTERAÇÃO DE STATUS
    @Transactional
    public HistoricoStatusResponse salvar(HistoricoStatusRequest dto) {
        Participacao participacao = participacaoRepository.findById(dto.participacaoId())
                .orElseThrow(() -> new ResourceNotFoundException("Participação não encontrada com o ID: " + dto.participacaoId()));

        Usuario usuario = null;
        if (dto.usuarioId() != null) {
            usuario = usuarioRepository.findById(dto.usuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + dto.usuarioId()));
        }

        HistoricoStatus historico = HistoricoStatus.builder()
                .status(dto.status())
                .observacao(dto.observacao())
                .participacao(participacao)
                .usuario(usuario)
                .build();

        return HistoricoStatusResponse.fromEntity(historicoStatusRepository.save(historico));
    }
}