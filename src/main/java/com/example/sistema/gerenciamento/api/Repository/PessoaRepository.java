package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
