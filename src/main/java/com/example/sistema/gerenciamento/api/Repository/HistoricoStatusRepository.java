package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.HistoricoStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Consultas do historico de alteracoes de status das participacoes. */
@Repository
public interface HistoricoStatusRepository extends JpaRepository<HistoricoStatus, Long> {

    /** Retorna o historico mais recente primeiro. */
    List<HistoricoStatus> findByParticipacaoIdOrderByDataAlteracaoDesc(Long participacaoId);
}