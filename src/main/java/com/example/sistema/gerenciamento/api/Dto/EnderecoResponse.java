package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.Endereco;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String numero,
        String complemento
) {
    public static EnderecoResponse fromEntity(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        return new EnderecoResponse(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getNumero(),
                endereco.getComplemento()
        );
    }
}