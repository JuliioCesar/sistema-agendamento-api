package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.Participacao;
import com.example.sistema.gerenciamento.api.Entity.StatusAtividade;
import java.time.LocalDateTime;

public record ParticipacaoResponse(
        Long id,
        LocalDateTime dataHora,
        StatusAtividade status,
        Long pessoaId,
        String pessoaNome,
        Long servicoId,
        String servicoNome
) {
    public static ParticipacaoResponse fromEntity(Participacao participacao) {
        if (participacao == null) {
            return null;
        }

        return new ParticipacaoResponse(
                participacao.getId(),
                participacao.getDataHora(),
                participacao.getStatus(),
                participacao.getPessoa() != null ? participacao.getPessoa().getId() : null,
                participacao.getPessoa() != null ? participacao.getPessoa().getNome() : null,
                participacao.getServico() != null ? participacao.getServico().getId() : null,
                participacao.getServico() != null ? participacao.getServico().getNome() : null
        );
    }
}