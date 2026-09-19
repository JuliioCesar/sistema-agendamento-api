package com.example.sistema.gerenciamento.api.Exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

/** Testes unitarios do contrato de erro exposto pelo handler global. */
class GlobalExceptionHandlerTest {

    /** Handler testado diretamente, sem subir o servidor web. */
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    /** Confirma que uma regra de negocio resulta em HTTP 400. */
    @Test
    void deveTratarBusinessExceptionComoBadRequest() {
        // Arrange: simula uma rota e uma regra de negocio violada.
        MockHttpServletRequest request = requestFor("/api/pessoas");
        BusinessException exception = new BusinessException("CPF ja cadastrado");

        // Act: delega o tratamento ao metodo global.
        ResponseEntity<StandardError> response = handler.tratarRegraDeNegocio(exception, request);

        // Assert: valida status e todos os dados essenciais do DTO.
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("CPF ja cadastrado");
        assertThat(response.getBody().getPath()).isEqualTo("/api/pessoas");
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }

    /** Confirma que recurso inexistente resulta em HTTP 404. */
    @Test
    void deveTratarResourceNotFoundComoNotFound() {
        // Arrange: cria a excecao e informa o caminho consultado.
        MockHttpServletRequest request = requestFor("/api/pessoas/99");
        ResourceNotFoundException exception = new ResourceNotFoundException("Pessoa nao encontrada");

        // Act: executa o tratamento do erro de recurso.
        ResponseEntity<StandardError> response = handler.tratarNaoEncontrado(exception, request);

        // Assert: garante que o contrato comunica ausencia do recurso.
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(404);
        assertThat(response.getBody().getError()).isEqualTo("Not Found");
        assertThat(response.getBody().getPath()).isEqualTo("/api/pessoas/99");
    }

    /** Confirma que falhas nao previstas resultam em HTTP 500 sem detalhes internos. */
    @Test
    void deveTratarErroGenericoComoInternalServerError() {
        // Arrange: simula uma excecao tecnica inesperada.
        MockHttpServletRequest request = requestFor("/api/participacoes");
        RuntimeException exception = new RuntimeException("detalhe interno");

        // Act: chama o fallback generico do handler.
        ResponseEntity<StandardError> response = handler.tratarErroInesperado(exception, request);

        // Assert: valida a mensagem segura e o status interno.
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(500);
        assertThat(response.getBody().getMessage()).isEqualTo("Ocorreu um erro interno inesperado.");
        assertThat(response.getBody().getPath()).isEqualTo("/api/participacoes");
    }

    /** Cria uma requisicao falsa com o URI usado no campo path do erro. */
    private MockHttpServletRequest requestFor(String uri) {
        // Arrange auxiliar: o mock reproduz somente o dado necessario pelo handler.
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI(uri);
        return request;
    }
}
