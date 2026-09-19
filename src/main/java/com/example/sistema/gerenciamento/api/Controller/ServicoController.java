package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Entity.Servico;
import com.example.sistema.gerenciamento.api.Service.ServicoService;
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
@RequestMapping("/api/servicos")
public class ServicoController {

	private final ServicoService service;

	public ServicoController(ServicoService service) { this.service = service; }
	@GetMapping public List<Servico> listar() { return service.listar(); }
	@GetMapping("/{id}") public Servico buscar(@PathVariable Long id) { return service.buscar(id); }
	@PostMapping @ResponseStatus(HttpStatus.CREATED)
	public Servico criar(@Valid @RequestBody Servico servico) { return service.salvar(servico); }
	@PutMapping("/{id}") public Servico atualizar(@PathVariable Long id, @Valid @RequestBody Servico servico) {
		return service.atualizar(id, servico);
	}
	@DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) { service.excluir(id); }
}
