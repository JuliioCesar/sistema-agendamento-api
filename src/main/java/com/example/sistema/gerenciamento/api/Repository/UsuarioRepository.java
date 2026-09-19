package com.example.sistema.gerenciamento.api.Repository;

import com.example.sistema.gerenciamento.api.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Consultas de autenticacao e validacao de operadores do sistema. */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/** Busca o usuario pelo email usado no login. */
	Optional<Usuario> findByEmail(String email);

	/** Verifica se o email ja esta associado a outro usuario. */
	boolean existsByEmail(String email);

	/** Verifica se o CPF ja esta associado a outro usuario. */
	boolean existsByCpf(String cpf);
}