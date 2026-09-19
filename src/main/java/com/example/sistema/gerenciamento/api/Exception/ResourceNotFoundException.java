package com.example.sistema.gerenciamento.api.Exception;

/** Excecao para quando um recurso solicitado nao existe. */
public class ResourceNotFoundException extends RuntimeException {

    /** Cria a excecao com a mensagem que sera retornada ao consumidor. */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}