package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.HistoricoStatus;
import com.example.sistema.gerenciamento.api.Entity.StatusAtividade;
import java.time.LocalDateTime;

public record HistoricoStatusResponse(
        Long id,
        StatusAtividade status,
        String observacao,
        LocalDateTime dataAlteracao,
        Long participacaoId,
        Long usuarioId,
        String nomeUsuario
) {
    public static HistoricoStatusResponse fromEntity(HistoricoStatus historico) {
        return new HistoricoStatusResponse(
                historico.getId(),
                historico.getStatus(),
                historico.getObservacao(),
                historico.getDataAlteracao(),
                historico.getParticipacao() != null ? historico.getParticipacao().getId() : null,
                historico.getUsuario() != null ? historico.getUsuario().getId() : null,
                historico.getUsuario() != null ? historico.getUsuario().getNomeCompleto() : null
        );
    }
}