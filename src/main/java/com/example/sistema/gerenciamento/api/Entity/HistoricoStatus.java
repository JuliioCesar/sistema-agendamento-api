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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime alteradoEm;

	@Enumerated(EnumType.STRING)
	@NotNull
	private StatusAtividade status;

	private String observacao;

	@ManyToOne
	@JoinColumn(name = "participacao_id", nullable = false)
	private Participacao participacao;
}
