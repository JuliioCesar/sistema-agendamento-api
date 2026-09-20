package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Pessoa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // [ GET ] - BUSCA UMA PESSOA PELO CPF EXATO
    Optional<Pessoa> findByCpf(String cpf);

    // [ GET ] - VERIFICA SE JÁ EXISTE PESSOA CADASTRADA COM O CPF INFORMADO
    boolean existsByCpf(String cpf);

    // [ GET ] - VERIFICA SE JÁ EXISTE PESSOA CADASTRADA COM O E-MAIL INFORMADO
    boolean existsByEmail(String email);

    // [ GET ] - BUSCA UMA PESSOA PELA MATRÍCULA EXATA
    Optional<Pessoa> findByMatricula(String matricula);

    // [ GET ] - VERIFICA SE JÁ EXISTE PESSOA CADASTRADA COM A MATRÍCULA INFORMADA
    boolean existsByMatricula(String matricula);

    // [ GET ] - PESQUISA POR PARTE DO NOME OU DO CPF IGNORANDO MAIÚSCULAS E MINÚSCULAS
    List<Pessoa> findByNomeContainingIgnoreCaseOrCpfContaining(String nome, String cpf);
}