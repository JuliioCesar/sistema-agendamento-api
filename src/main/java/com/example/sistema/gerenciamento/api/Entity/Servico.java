package com.example.sistema.gerenciamento.api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Servico que pode ser agendado por uma pessoa. */
// @Entity mapeia esta classe para uma tabela relacional.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

	/** Chave primaria gerada pelo banco. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Nome obrigatorio apresentado aos usuarios. */
	@NotBlank
	private String nome;

	/** Categoria usada nas consultas de filtragem. */
	private String categoria;

	/** Descricao complementar do servico. */
	private String descricao;

	/** Duracao prevista em minutos; deve ser positiva quando informada. */
	@Min(1)
	private Integer duracaoMinutos;

	/** Sinaliza se o servico pode receber novas participacoes. */
	private boolean ativo = true;

	// @ManyToOne permite que um responsavel gerencie varios servicos.
	@ManyToOne
	// nullable=false garante a integridade da chave estrangeira.
	@JoinColumn(name = "responsavel_id", nullable = false)
	/** Responsavel obrigatorio pelo servico. */
	private Responsavel responsavel;
}
