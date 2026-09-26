package com.example.sistema.gerenciamento.api.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResponsavelRequest(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 150, message = "O nome não pode ter mais de 150 caracteres")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail informado é inválido")
        @Size(max = 100, message = "O e-mail não pode ter mais de 100 caracteres")
        String email,

        @Size(max = 20, message = "O telefone não pode ter mais de 20 caracteres")
        String telefone
) {}