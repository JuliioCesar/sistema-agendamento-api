package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.StatusAtividade;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusRequest(
        @NotNull StatusAtividade status,
        String observacao) {
} 