package com.example.sistema.gerenciamento.api.Exception;

/** Excecao para falhas de autenticacao, como login ou senha invalidos. */
public class UnauthorizedException extends RuntimeException {

    /** Cria a excecao com a mensagem de autenticacao recusada. */
    public UnauthorizedException(String message) {
        super(message);
    }
}