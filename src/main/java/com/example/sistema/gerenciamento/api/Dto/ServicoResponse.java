package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.Servico;

public record ServicoResponse(
        Long id,
        String nome,
        String categoria,
        String descricao,
        Integer duracaoMinutos,
        boolean ativo,
        Long responsavelId,
        String responsavelNome
) {
    public static ServicoResponse fromEntity(Servico servico) {
        if (servico == null) {
            return null;
        }

        return new ServicoResponse(
                servico.getId(),
                servico.getNome(),
                servico.getCategoria(),
                servico.getDescricao(),
                servico.getDuracaoMinutos(),
                servico.isAtivo(),
                servico.getResponsavel() != null ? servico.getResponsavel().getId() : null,
                servico.getResponsavel() != null ? servico.getResponsavel().getNome() : null
        );
    }
}