package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.Pessoa;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PessoaResponse(
        Long id,
        String nome,
        String matricula,
        String email,
        String cpf,
        String telefone,
        LocalDate dataNascimento,
        LocalDateTime dataCadastro,
        boolean menorDeIdade,
        EnderecoResponse endereco,
        Long responsavelId
) {
    public static PessoaResponse fromEntity(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        return new PessoaResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getMatricula(),
                pessoa.getEmail(),
                pessoa.getCpf(),
                pessoa.getTelefone(),
                pessoa.getDataNascimento(),
                pessoa.getDataCadastro(),
                pessoa.isMenorDeIdade(),
                pessoa.getEndereco() != null ? EnderecoResponse.fromEntity(pessoa.getEndereco()) : null,
                pessoa.getResponsavel() != null ? pessoa.getResponsavel().getId() : null
        );
    }
}