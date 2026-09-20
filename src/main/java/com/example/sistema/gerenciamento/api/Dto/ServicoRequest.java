package com.example.sistema.gerenciamento.api.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ServicoRequest(
        @NotBlank(message = "O nome do serviço é obrigatório")
        @Size(max = 150, message = "O nome não pode ter mais de 150 caracteres")
        String nome,

        @Size(max = 100, message = "A categoria não pode ter mais de 100 caracteres")
        String categoria,

        @Size(max = 500, message = "A descrição não pode ter mais de 500 caracteres")
        String descricao,

        @Min(value = 1, message = "A duração mínima deve ser de 1 minuto")
        Integer duracaoMinutos,

        Boolean ativo,

        @NotNull(message = "O ID do responsável é obrigatório")
        Long responsavelId
) {}