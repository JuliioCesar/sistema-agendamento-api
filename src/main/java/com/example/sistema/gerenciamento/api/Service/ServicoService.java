package com.example.sistema.gerenciamento.api.Service;

import com.example.sistema.gerenciamento.api.Entity.Servico;
import com.example.sistema.gerenciamento.api.Exception.RecursoNaoEncontradoException;
import com.example.sistema.gerenciamento.api.Repository.ServicoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    private final ServicoRepository repository;
    private final ResponsavelService responsavelService;

    public ServicoService(ServicoRepository repository, ResponsavelService responsavelService) {
        this.repository = repository;
        this.responsavelService = responsavelService;
    }

    public List<Servico> listar() { return repository.findAll(); }

    public Servico buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Serviço", id));
    }

    public Servico salvar(Servico servico) {
        servico.setResponsavel(responsavelService.buscar(servico.getResponsavel().getId()));
        return repository.save(servico);
    }

    public Servico atualizar(Long id, Servico dados) {
        Servico servico = buscar(id);
        servico.setNome(dados.getNome());
        servico.setDescricao(dados.getDescricao());
        servico.setDuracaoMinutos(dados.getDuracaoMinutos());
        servico.setAtivo(dados.isAtivo());
        servico.setResponsavel(responsavelService.buscar(dados.getResponsavel().getId()));
        return repository.save(servico);
    }

    public void excluir(Long id) { repository.delete(buscar(id)); }
}