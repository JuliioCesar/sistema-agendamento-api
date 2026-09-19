package com.example.sistema.gerenciamento.api.Entity;

/** Estados possiveis de uma atividade ou participacao. */
public enum StatusAtividade {
	/** Atividade liberada ou em andamento. */
	ATIVO,
	/** Atividade temporariamente indisponivel. */
	INATIVO,
	/** Atividade aguardando processamento. */
	PENDENTE,
	/** Atividade suspensa por alguma regra operacional. */
	SUSPENSO,
	/** Atividade encerrada definitivamente. */
	DESLIGADO,
	/** Status legado para agendamentos ainda nao confirmados. */
	AGENDADA,
	/** Status legado para agendamentos confirmados. */
	CONFIRMADA,
	/** Status legado para agendamentos concluidos. */
	CONCLUIDA,
	/** Status legado para agendamentos cancelados. */
	CANCELADA
}
