package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Dto.AtualizarStatusRequest;
import com.example.sistema.gerenciamento.api.Dto.ParticipacaoRequest;
import com.example.sistema.gerenciamento.api.Dto.ParticipacaoResponse;
import com.example.sistema.gerenciamento.api.Service.ParticipacaoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participacoes")
@RequiredArgsConstructor
public class ParticipacaoController {

    private final ParticipacaoService participacaoService;

    // [ GET ] - LISTA TODAS AS PARTICIPAÇÕES
    @GetMapping
    public ResponseEntity<List<ParticipacaoResponse>> listarTodas() {
        return ResponseEntity.ok(participacaoService.listarTodas());
    }

    // [ GET ] - BUSCA UMA PARTICIPAÇÃO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ParticipacaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(participacaoService.buscarPorId(id));
    }

    // [ GET ] - LISTA PARTICIPAÇÕES POR PESSOA
    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<ParticipacaoResponse>> listarPorPessoa(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(participacaoService.listarPorPessoa(pessoaId));
    }

    // [ GET ] - LISTA PARTICIPAÇÕES POR SERVIÇO
    @GetMapping("/servico/{servicoId}")
    public ResponseEntity<List<ParticipacaoResponse>> listarPorServico(@PathVariable Long servicoId) {
        return ResponseEntity.ok(participacaoService.listarPorServico(servicoId));
    }

    // [ POST ] - CRIA UMA NOVA PARTICIPAÇÃO
    @PostMapping
    public ResponseEntity<ParticipacaoResponse> salvar(@Valid @RequestBody ParticipacaoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participacaoService.salvar(dto));
    }

    // [ PATCH ] - ATUALIZA O STATUS DA PARTICIPAÇÃO E REGISTRA O HISTÓRICO
    @PatchMapping("/{id}/status")
    public ResponseEntity<ParticipacaoResponse> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusRequest request) {
        return ResponseEntity.ok(participacaoService.atualizarStatus(id, request));
    }

    // [ DELETE ] - EXCLUI UMA PARTICIPAÇÃO POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        participacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}