package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Entity.Pessoa;
import com.example.sistema.gerenciamento.api.Service.PessoaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

	private final PessoaService service;

	public PessoaController(PessoaService service) { this.service = service; }

	@GetMapping public List<Pessoa> listar() { return service.listar(); }
	@GetMapping("/{id}") public Pessoa buscar(@PathVariable Long id) { return service.buscar(id); }
	@PostMapping @ResponseStatus(HttpStatus.CREATED)
	public Pessoa criar(@Valid @RequestBody Pessoa pessoa) { return service.salvar(pessoa); }
	@PutMapping("/{id}") public Pessoa atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
		return service.atualizar(id, pessoa);
	}
	@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) { service.excluir(id); }
}
