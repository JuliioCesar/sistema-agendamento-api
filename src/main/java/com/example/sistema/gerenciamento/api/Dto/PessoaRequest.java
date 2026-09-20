package com.example.sistema.gerenciamento.api.Dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record PessoaRequest(
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 150, message = "O nome não pode ter mais de 150 caracteres.")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome não deve conter números ou caracteres especiais")
        String nome,

        @NotBlank(message = "A matrícula é obrigatória")
        @Size(max = 50, message = "A matrícula não pode ter mais de 50 caracteres")
        String matricula,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        @Size(max = 100, message = "O e-mail não pode ter mais de 100 caracteres")
        String email,

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 números")
        @Pattern(regexp = "^[0-9]{11}$", message = "O CPF deve conter apenas números (sem pontos ou traço)")
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        @Size(max = 20, message = "O telefone não pode ter mais de 20 caracteres")
        @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números")
        String telefone,

        LocalDate dataNascimento,

        @Valid
        EnderecoRequest endereco,

        Long responsavelId
) {}