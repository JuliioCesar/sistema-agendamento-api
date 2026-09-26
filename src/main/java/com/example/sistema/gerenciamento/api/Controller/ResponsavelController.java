package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Dto.ResponsavelRequest;
import com.example.sistema.gerenciamento.api.Dto.ResponsavelResponse;
import com.example.sistema.gerenciamento.api.Service.ResponsavelService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responsaveis")
@RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    // [ GET ] - RETORNA TODOS OS RESPONSÁVEIS
    @GetMapping
    public ResponseEntity<List<ResponsavelResponse>> listarTodos() {
        return ResponseEntity.ok(responsavelService.listarTodos());
    }

    // [ GET ] - BUSCA UM RESPONSÁVEL PELO ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(responsavelService.buscarPorId(id));
    }

    // [ GET ] - PESQUISA RESPONSÁVEIS POR NOME OU E-MAIL
    @GetMapping("/pesquisa")
    public ResponseEntity<List<ResponsavelResponse>> pesquisar(@RequestParam String termo) {
        return ResponseEntity.ok(responsavelService.pesquisar(termo));
    }

    // [ POST ] - CRIA UM NOVO RESPONSÁVEL
    @PostMapping
    public ResponseEntity<ResponsavelResponse> salvar(@Valid @RequestBody ResponsavelRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(responsavelService.salvar(dto));
    }

    // [ PUT ] - ATUALIZA UM RESPONSÁVEL EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<ResponsavelResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ResponsavelRequest dto) {
        return ResponseEntity.ok(responsavelService.atualizar(id, dto));
    }

    // [ DELETE ] - EXCLUI UM RESPONSÁVEL POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        responsavelService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}