package com.example.sistema.gerenciamento.api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
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
public class Participacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Future
	private LocalDateTime dataHora;

	@Enumerated(EnumType.STRING)
	private StatusAtividade status = StatusAtividade.AGENDADA;

	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "servico_id", nullable = false)
	private Servico servico;
    
}
