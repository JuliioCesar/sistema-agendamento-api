package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // [ GET ] - BUSCA UM USUÁRIO PELO E-MAIL EXATO
    Optional<Usuario> findByEmail(String email);

    // [ GET ] - BUSCA UM USUÁRIO PELO CPF EXATO
    Optional<Usuario> findByCpf(String cpf);

    // [ GET ] - VERIFICA SE JÁ EXISTE USUÁRIO CADASTRADO COM O E-MAIL INFORMADO
    boolean existsByEmail(String email);

    // [ GET ] - VERIFICA SE JÁ EXISTE USUÁRIO CADASTRADO COM O CPF INFORMADO
    boolean existsByCpf(String cpf);

    // [ GET ] - BUSCA APENAS OS USUÁRIOS QUE ESTÃO ATIVOS NO SISTEMA
    List<Usuario> findByAtivoTrue();

    // [ GET ] - PESQUISA USUÁRIOS POR PARTE DO NOME OU DO E-MAIL IGNORANDO MAIÚSCULAS E MINÚSCULAS
    List<Usuario> findByNomeCompletoContainingIgnoreCaseOrEmailContainingIgnoreCase(String nome, String email);
}