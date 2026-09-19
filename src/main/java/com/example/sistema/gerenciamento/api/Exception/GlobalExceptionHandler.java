package com.example.sistema.gerenciamento.api.Exception;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> tratarNaoEncontrado(RecursoNaoEncontradoException exception) {
        return Map.of("erro", exception.getMessage());
    }
}