package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Dto.ServicoRequest;
import com.example.sistema.gerenciamento.api.Dto.ServicoResponse;
import com.example.sistema.gerenciamento.api.Service.ServicoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    // [ GET ] - LISTAR TODOS OS SERVIÇOS CADASTRADOS
    @GetMapping
    public ResponseEntity<List<ServicoResponse>> listarTodos() {
        return ResponseEntity.ok(servicoService.listarTodos());
    }

    // [ GET ] - LISTAR APENAS SERVIÇOS ATIVOS
    @GetMapping("/ativos")
    public ResponseEntity<List<ServicoResponse>> listarAtivos() {
        return ResponseEntity.ok(servicoService.listarAtivos());
    }

    // [ GET ] - BUSCA SERVIÇO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }

    // [ POST ] - CADASTRO DE NOVO SERVIÇO COM VALIDAÇÃO
    @PostMapping
    public ResponseEntity<ServicoResponse> criar(@Valid @RequestBody ServicoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.salvar(request));
    }

    // [ PUT ] - ATUALIZA DADOS DE UM SERVIÇO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ServicoRequest request) {
        return ResponseEntity.ok(servicoService.atualizar(id, request));
    }

    // [ DELETE ] - EXCLUI SERVIÇO DO SISTEMA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}