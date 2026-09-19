package com.example.sistema.gerenciamento.api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Registro imutavel de cada alteracao de status de uma participacao. */
// @Entity permite consultar o historico por participacao.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoStatus {

	/** Identificador do evento de historico. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Momento em que o status foi alterado. */
	@NotNull
	private LocalDateTime dataAlteracao;

	// O nome do enum e persistido para manter os dados legiveis.
	@Enumerated(EnumType.STRING)
	@NotNull
	/** Novo status aplicado a participacao. */
	private StatusAtividade status;

	/** Motivo ou observacao opcional da alteracao. */
	private String observacao;

	// Muitos eventos podem pertencer a uma mesma participacao.
	@ManyToOne
	// O historico nao pode existir sem a participacao de origem.
	@JoinColumn(name = "participacao_id", nullable = false)
	/** Participacao cujo status foi alterado. */
	private Participacao participacao;

	// Pode ser nulo enquanto o fluxo de autenticacao ainda nao estiver integrado.
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	/** Operador que realizou a alteracao, quando identificado. */
	private Usuario usuario;
}
