package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Participacao;
import com.example.sistema.gerenciamento.api.Entity.StatusAtividade;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Long> {

    // [ GET ] - LISTA PARTICIPAÇÕES DE UMA PESSOA ESPECÍFICA
    List<Participacao> findByPessoaId(Long pessoaId);

    // [ GET ] - LISTA PARTICIPAÇÕES DE UM SERVIÇO ESPECÍFICO
    List<Participacao> findByServicoId(Long servicoId);

    // [ GET ] - LISTA PARTICIPAÇÕES FILTRADAS POR STATUS
    List<Participacao> findByStatus(StatusAtividade status);

    // [ GET ] - VERIFICA SE JÁ EXISTE AGENDAMENTO DA MESMA PESSOA NO MESMO SERVIÇO E HORÁRIO
    boolean existsByPessoaIdAndServicoIdAndDataHora(Long pessoaId, Long servicoId, LocalDateTime dataHora);
}