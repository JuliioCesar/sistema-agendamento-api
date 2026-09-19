package com.example.sistema.gerenciamento.api.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Pessoa atendida pelo sistema de gerenciamento social. */
// @Entity marca a classe como uma entidade persistente do JPA.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa {

	/** Identificador tecnico gerado pelo banco. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Nome obrigatorio da pessoa. */
	@NotBlank
	private String nome;

	/** Matricula unica usada para localizar rapidamente o cadastro. */
	@Column(nullable = false, unique = true, length = 50)
	@NotBlank
	private String matricula;

	/** Email de contato validado pelo Bean Validation. */
	@Email
	@NotBlank
	private String email;

	/** CPF usado para identificar a pessoa e impedir cadastros duplicados. */
	@Column(unique = true, length = 14)
	private String cpf;

	/** Telefone opcional de contato. */
	private String telefone;

	/** Data usada pelo metodo de calculo de maioridade. */
	private LocalDate dataNascimento;

	// @Embedded grava os campos de Endereco na mesma tabela de Pessoa.
	@Embedded
	/** Endereco opcional composto por varios campos simples. */
	private Endereco endereco;

	// @OneToOne representa no maximo um responsavel para cada pessoa.
	@OneToOne
	// A ausencia de nullable=false permite cadastrar pessoa sem responsavel.
	@JoinColumn(name = "responsavel_id")
	/** Responsavel legal opcional da pessoa. */
	private Responsavel responsavel;

	/** Indica se a pessoa tinha menos de 18 anos na data atual. */
	public boolean isMenorDeIdade() {
		return dataNascimento != null
				&& Period.between(dataNascimento, LocalDate.now()).getYears() < 18;
	}
}
