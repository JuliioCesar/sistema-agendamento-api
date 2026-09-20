package com.example.sistema.gerenciamento.api.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(
        @NotBlank(message = "O CEP é obrigatório")
        @Size(min = 8, max = 8, message = "O CEP deve conter exatamente 8 dígitos")
        @Pattern(regexp = "^[0-9]{8}$", message = "O CEP deve conter apenas números")
        String cep,

        @NotBlank(message = "O logradouro é obrigatório")
        @Size(max = 150, message = "O logradouro não pode ter mais de 150 caracteres")
        String logradouro,

        @NotBlank(message = "O bairro é obrigatório")
        @Size(max = 100, message = "O bairro não pode ter mais de 100 caracteres")
        String bairro,

        @NotBlank(message = "A cidade é obrigatória")
        @Size(max = 100, message = "A cidade não pode ter mais de 100 caracteres")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        @Size(min = 2, max = 2, message = "O estado deve conter exatamente 2 letras")
        String estado,

        @NotBlank(message = "O número é obrigatório")
        @Size(max = 20, message = "O número não pode ter mais de 20 caracteres")
        String numero,

        @Size(max = 100, message = "O complemento não pode ter mais de 100 caracteres")
        String complemento
) {}