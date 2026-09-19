package com.example.sistema.gerenciamento.api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Operador do sistema, usado para autenticacao e identificacao das alteracoes. */
// @Entity transforma esta classe em uma tabela gerenciada pelo Hibernate.
@Entity
// @Table define um nome explicito para evitar depender do naming strategy.
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

	/** Chave primaria gerada automaticamente pelo banco. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Nome exibido nos registros e no perfil do operador. */
	@NotBlank
	@Column(nullable = false, length = 150)
	private String nomeCompleto;

	/** Email usado no login; a coluna impede duplicidade no banco. */
	@Email
	@NotBlank
	@Column(nullable = false, unique = true, length = 150)
	private String email;

	/** Senha armazenada pela camada de autenticacao. */
	@NotBlank
	@Column(nullable = false)
	private String senha;

	/** Telefone opcional para contato do operador. */
	private String telefone;

	/** CPF opcional, mas unico quando informado. */
	@Column(unique = true, length = 14)
	private String cpf;

	/** URL da foto de perfil, quando cadastrada. */
	private String fotoUrl;

	/** Token exclusivo usado no convite de cadastro. */
	@Column(unique = true, length = 100)
	private String tokenConvite;

	/** Data de nascimento usada para dados cadastrais do operador. */
	private LocalDate dataNascimento;

	/** Indica se o operador ainda pode acessar o sistema. */
	@Column(nullable = false)
	@Builder.Default
	private boolean ativo = true;
}