package com.example.sistema.gerenciamento.api.Controller;

import com.example.sistema.gerenciamento.api.Entity.HistoricoStatus;
import com.example.sistema.gerenciamento.api.Service.HistoricoStatusService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/participacoes/{participacaoId}/historico")
public class HistoricoStatusController {
    
	private final HistoricoStatusService service;

	public HistoricoStatusController(HistoricoStatusService service) { this.service = service; }

	@GetMapping
	public List<HistoricoStatus> listar(@PathVariable Long participacaoId) {
		return service.listarPorParticipacao(participacaoId);
	}
}
