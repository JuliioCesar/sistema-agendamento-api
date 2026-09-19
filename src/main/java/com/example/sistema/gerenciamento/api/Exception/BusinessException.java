package com.example.sistema.gerenciamento.api.Exception;

/** Excecao para regras de negocio violadas ou dados semanticamente invalidos. */
public class BusinessException extends RuntimeException {

    /** Cria a excecao com a descricao da regra violada. */
    public BusinessException(String message) {
        super(message);
    }
}