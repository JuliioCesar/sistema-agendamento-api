package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Dto.UsuarioRequest;
import com.example.sistema.gerenciamento.api.Dto.UsuarioResponse;
import com.example.sistema.gerenciamento.api.Service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // [ GET ] - RETORNA A LISTA COMPLETA DE USUÁRIOS CADASTRADOS
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // [ GET ] - RETORNA APENAS OS USUÁRIOS QUE ESTÃO ATIVOS
    @GetMapping("/ativos")
    public ResponseEntity<List<UsuarioResponse>> listarAtivos() {
        return ResponseEntity.ok(usuarioService.listarAtivos());
    }

    // [ GET ] - BUSCA UM USUÁRIO ESPECÍFICO PELO SEU ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    // [ POST ] - CADASTRA UM NOVO USUÁRIO NO SISTEMA
    @PostMapping
    public ResponseEntity<UsuarioResponse> salvar(@Valid @RequestBody UsuarioRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(dto));
    }

    // [ PUT ] - ATUALIZA OS DADOS DE UM USUÁRIO EXISTENTE PELO ID
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequest dto) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    // [ DELETE ] - REMOVE UM USUÁRIO DO SISTEMA PELO ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}