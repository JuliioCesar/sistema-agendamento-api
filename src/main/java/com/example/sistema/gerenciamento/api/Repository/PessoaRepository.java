package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Consultas de persistencia e filtros para pessoas atendidas pelo sistema. */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    /** Busca uma pessoa pelo CPF exato. */
    Optional<Pessoa> findByCpf(String cpf);

    /** Verifica se ja existe pessoa cadastrada com o CPF informado. */
    boolean existsByCpf(String cpf);

    /** Verifica se ja existe pessoa cadastrada com o e-mail informado. */
    boolean existsByEmail(String email);

    /** Busca uma pessoa pela matricula exata. */
    Optional<Pessoa> findByMatricula(String matricula);

    /** Verifica se ja existe pessoa cadastrada com a matricula informada. */
    boolean existsByMatricula(String matricula);

    /** Pesquisa por parte do nome ou do CPF, sem diferenciar maiusculas. */
    List<Pessoa> findByNomeContainingIgnoreCaseOrCpfContaining(String nome, String cpf);
}