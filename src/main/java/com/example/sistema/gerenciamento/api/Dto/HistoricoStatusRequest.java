package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.StatusAtividade;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HistoricoStatusRequest(
        @NotNull(message = "O status é obrigatório")
        StatusAtividade status,

        @Size(max = 500, message = "A observação não pode ter mais de 500 caracteres")
        String observacao,

        @NotNull(message = "O ID da participação é obrigatório")
        Long participacaoId,

        Long usuarioId
) {}