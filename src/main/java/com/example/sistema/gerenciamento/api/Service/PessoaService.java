package com.example.sistema.gerenciamento.api.Service;

import com.example.sistema.gerenciamento.api.Entity.Pessoa;
import com.example.sistema.gerenciamento.api.Exception.RecursoNaoEncontradoException;
import com.example.sistema.gerenciamento.api.Repository.PessoaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public List<Pessoa> listar() { return repository.findAll(); }

    public Pessoa buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa", id));
    }

    public Pessoa salvar(Pessoa pessoa) { return repository.save(pessoa); }

    public Pessoa atualizar(Long id, Pessoa dados) {
        Pessoa pessoa = buscar(id);
        pessoa.setNome(dados.getNome());
        pessoa.setEmail(dados.getEmail());
        pessoa.setTelefone(dados.getTelefone());
        return repository.save(pessoa);
    }

    public void excluir(Long id) { repository.delete(buscar(id)); }
}