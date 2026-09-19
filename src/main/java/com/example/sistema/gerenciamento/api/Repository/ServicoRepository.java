package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
