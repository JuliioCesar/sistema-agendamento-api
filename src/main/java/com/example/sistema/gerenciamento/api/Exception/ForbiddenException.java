package com.example.sistema.gerenciamento.api.Exception;

/** Excecao para usuario autenticado sem permissao para a operacao. */
public class ForbiddenException extends RuntimeException {

    /** Cria a excecao com a mensagem de permissao negada. */
    public ForbiddenException(String message) {
        super(message);
    }
}