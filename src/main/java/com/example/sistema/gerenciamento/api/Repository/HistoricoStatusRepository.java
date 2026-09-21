package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.HistoricoStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoStatusRepository extends JpaRepository<HistoricoStatus, Long> {

    // [ GET ] - LISTA O HISTÓRICO DE ALTERAÇÕES DE UMA PARTICIPAÇÃO ESPECÍFICA
    List<HistoricoStatus> findByParticipacaoIdOrderByDataAlteracaoDesc(Long participacaoId);

    // [ GET ] - LISTA AS ALTERAÇÕES REALIZADAS POR UM USUÁRIO ESPECÍFICO
    List<HistoricoStatus> findByUsuarioIdOrderByDataAlteracaoDesc(Long usuarioId);
}