package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Entity.StatusAtividade;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status-atividades")
public class StatusAtividadeController {

	@GetMapping
	public List<StatusAtividade> listar() { return Arrays.asList(StatusAtividade.values()); }
}
