package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.Responsavel;

public record ResponsavelResponse(
        Long id,
        String nome,
        String email,
        String telefone
) {
    public static ResponsavelResponse fromEntity(Responsavel responsavel) {
        if (responsavel == null) {
            return null;
        }

        return new ResponsavelResponse(
                responsavel.getId(),
                responsavel.getNome(),
                responsavel.getEmail(),
                responsavel.getTelefone()
        );
    }
}