package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Servico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Consultas de persistencia para servicos oferecidos. */
@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	/** Lista somente os servicos atualmente ativos. */
	List<Servico> findByAtivoTrue();

	/** Filtra servicos pela categoria informada. */
	List<Servico> findByCategoria(String categoria);
}
