package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Dto.PessoaRequest;
import com.example.sistema.gerenciamento.api.Dto.PessoaResponse;
import com.example.sistema.gerenciamento.api.Service.PessoaService;
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
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    // [ GET ] - LISTAR TODAS AS PESSOAS CADASTRADAS
    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listarTodas() {
        return ResponseEntity.ok(pessoaService.listar());
    }

    // [ GET ] - BUSCA PESSOA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscar(id));
    }

    // [ POST ] - CADASTRO DE NOVA PESSOA COM VALIDAÇÃO
    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@Valid @RequestBody PessoaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.salvar(request));
    }

    // [ PUT ] - ATUALIZA DADOS DE UMA PESSOA EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PessoaRequest dadosAtualizados) {
        return ResponseEntity.ok(pessoaService.atualizar(id, dadosAtualizados));
    }

    // [ DELETE ] - EXCLUI PESSOA DO SISTEMA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}