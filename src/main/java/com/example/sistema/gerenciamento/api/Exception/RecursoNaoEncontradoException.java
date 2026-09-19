package com.example.sistema.gerenciamento.api.Exception;

/**
 * Nome legado mantido para nao quebrar os servicos existentes.
 * A classe base padronizada e ResourceNotFoundException.
 */
public class RecursoNaoEncontradoException extends ResourceNotFoundException {

    public RecursoNaoEncontradoException(String recurso, Long id) {
        super(recurso + " não encontrado: " + id);
    }
}