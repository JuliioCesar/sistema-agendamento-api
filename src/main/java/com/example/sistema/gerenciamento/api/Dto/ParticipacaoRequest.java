package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.StatusAtividade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ParticipacaoRequest(
        @NotNull(message = "A data e hora são obrigatórias")
        @Future(message = "A data e hora do agendamento devem ser no futuro")
        LocalDateTime dataHora,

        @NotNull(message = "O ID da pessoa é obrigatório")
        Long pessoaId,

        @NotNull(message = "O ID do serviço é obrigatório")
        Long servicoId,

        StatusAtividade status
) {}