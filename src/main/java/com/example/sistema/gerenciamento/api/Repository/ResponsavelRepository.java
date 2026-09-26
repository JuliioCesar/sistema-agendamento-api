package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Responsavel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    // [ GET ] - BUSCA UM RESPONSÁVEL PELO E-MAIL EXATO
    Optional<Responsavel> findByEmail(String email);

    // [ GET ] - VERIFICA SE JÁ EXISTE RESPONSÁVEL CADASTRADO COM O E-MAIL INFORMADO
    boolean existsByEmail(String email);

    // [ GET ] - PESQUISA RESPONSÁVEIS POR PARTE DO NOME OU DO E-MAIL IGNORANDO MAIÚSCULAS E MINÚSCULAS
    List<Responsavel> findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(String nome, String email);
}