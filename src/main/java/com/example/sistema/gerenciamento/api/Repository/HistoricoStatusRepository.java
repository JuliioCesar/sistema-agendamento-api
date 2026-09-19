package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.HistoricoStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoStatusRepository extends JpaRepository<HistoricoStatus, Long> {

    List<HistoricoStatus> findByParticipacaoIdOrderByAlteradoEmDesc(Long participacaoId);
}