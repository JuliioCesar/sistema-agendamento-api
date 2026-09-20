package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Servico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	// [ GET ] - BUSCA APENAS OS SERVIÇOS QUE ESTÃO ATIVOS
    List<Servico> findByAtivoTrue();

	// [ GET ] - BUSCA SERVIÇOS POR CATEGORIA IGNORANDO MAIÚSCULAS E MINÚSCULAS
    List<Servico> findByCategoriaIgnoreCase(String categoria);

	// [ GET ] - VERIFICA SE JÁ EXISTE UM SERVIÇO CADASTRADO COM O MESMO NOME
    boolean existsByNomeIgnoreCase(String nome);
}