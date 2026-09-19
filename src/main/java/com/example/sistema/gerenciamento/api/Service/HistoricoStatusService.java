package com.example.sistema.gerenciamento.api.Service;

import com.example.sistema.gerenciamento.api.Entity.HistoricoStatus;
import com.example.sistema.gerenciamento.api.Repository.HistoricoStatusRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HistoricoStatusService {

    private final HistoricoStatusRepository repository;

    public HistoricoStatusService(HistoricoStatusRepository repository) { this.repository = repository; }

    public List<HistoricoStatus> listarPorParticipacao(Long participacaoId) {
        return repository.findByParticipacaoIdOrderByDataAlteracaoDesc(participacaoId);
    }
}