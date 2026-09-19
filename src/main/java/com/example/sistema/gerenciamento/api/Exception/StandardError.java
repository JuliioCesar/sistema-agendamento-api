package com.example.sistema.gerenciamento.api.Exception;

import java.time.Instant;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** DTO usado para manter uma resposta de erro uniforme em toda a API. */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {

    /** Momento em que o erro foi produzido. */
    private Instant timestamp;

    /** Codigo HTTP devolvido ao cliente. */
    private Integer status;

    /** Descricao resumida do status HTTP. */
    private String error;

    /** Mensagem adequada para o consumidor da API. */
    private String message;

    /** Rota que originou o erro. */
    private String path;

    /** Erros de validacao indexados pelo nome do campo. */
    private Map<String, String> errors;
}