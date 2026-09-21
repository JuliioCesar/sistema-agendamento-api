package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Dto.HistoricoStatusRequest;
import com.example.sistema.gerenciamento.api.Dto.HistoricoStatusResponse;
import com.example.sistema.gerenciamento.api.Service.HistoricoStatusService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historicos-status")
@RequiredArgsConstructor
public class HistoricoStatusController {

    private final HistoricoStatusService historicoStatusService;

    // [ GET ] - RETORNA TODOS OS REGISTROS DE HISTÓRICO
    @GetMapping
    public ResponseEntity<List<HistoricoStatusResponse>> listarTodos() {
        return ResponseEntity.ok(historicoStatusService.listarTodos());
    }

    // [ GET ] - BUSCA UM REGISTRO DE HISTÓRICO ESPECÍFICO PELO ID
    @GetMapping("/{id}")
    public ResponseEntity<HistoricoStatusResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(historicoStatusService.buscarPorId(id));
    }

    // [ GET ] - RETORNA O HISTÓRICO DE UMA PARTICIPAÇÃO ESPECÍFICA
    @GetMapping("/participacao/{participacaoId}")
    public ResponseEntity<List<HistoricoStatusResponse>> listarPorParticipacao(@PathVariable Long participacaoId) {
        return ResponseEntity.ok(historicoStatusService.listarPorParticipacao(participacaoId));
    }

    // [ POST ] - REGISTRA UMA NOVA ALTERAÇÃO DE STATUS NO HISTÓRICO
    @PostMapping
    public ResponseEntity<HistoricoStatusResponse> salvar(@Valid @RequestBody HistoricoStatusRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historicoStatusService.salvar(dto));
    }
}