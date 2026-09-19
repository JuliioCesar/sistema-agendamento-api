package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Participacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipacaoRepository extends JpaRepository<Participacao, Long> {
}
