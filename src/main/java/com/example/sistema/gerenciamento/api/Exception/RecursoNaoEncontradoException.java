package com.example.sistema.gerenciamento.api.Exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String recurso, Long id) {
        super(recurso + " não encontrado: " + id);
    }
}