package com.example.sistema.gerenciamento.api.Service;

import com.example.sistema.gerenciamento.api.Dto.PessoaRequest;
import com.example.sistema.gerenciamento.api.Dto.PessoaResponse;
import com.example.sistema.gerenciamento.api.Entity.Endereco;
import com.example.sistema.gerenciamento.api.Entity.Pessoa;
import com.example.sistema.gerenciamento.api.Entity.Responsavel;
import com.example.sistema.gerenciamento.api.Exception.ResourceNotFoundException;
import com.example.sistema.gerenciamento.api.Repository.PessoaRepository;
import com.example.sistema.gerenciamento.api.Repository.ResponsavelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ResponsavelRepository responsavelRepository;

    // [ GET ] - LISTA TODAS AS PESSOAS
    @Transactional(readOnly = true)
    public List<PessoaResponse> listar() {
        return pessoaRepository.findAll().stream()
                .map(PessoaResponse::fromEntity)
                .toList();
    }

    // [ GET ] - BUSCA PESSOA POR ID
    @Transactional(readOnly = true)
    public PessoaResponse buscar(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));
        return PessoaResponse.fromEntity(pessoa);
    }

    // [ POST ] - CADASTRA UMA NOVA PESSOA
    @Transactional
    public PessoaResponse salvar(PessoaRequest dto) {
        Responsavel responsavel = null;
        if (dto.responsavelId() != null) {
            responsavel = responsavelRepository.findById(dto.responsavelId())
                    .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado com o ID: " + dto.responsavelId()));
        }

        Endereco endereco = null;
        if (dto.endereco() != null) {
            endereco = Endereco.builder()
                    .cep(dto.endereco().cep())
                    .logradouro(dto.endereco().logradouro())
                    .bairro(dto.endereco().bairro())
                    .cidade(dto.endereco().cidade())
                    .estado(dto.endereco().estado())
                    .numero(dto.endereco().numero())
                    .complemento(dto.endereco().complemento())
                    .build();
        }

        Pessoa pessoa = Pessoa.builder()
                .nome(dto.nome())
                .matricula(dto.matricula())
                .email(dto.email())
                .cpf(dto.cpf())
                .telefone(dto.telefone())
                .dataNascimento(dto.dataNascimento())
                .endereco(endereco)
                .responsavel(responsavel)
                .build();

        return PessoaResponse.fromEntity(pessoaRepository.save(pessoa));
    }

    // [ PUT ] - ATUALIZA OS DADOS DA PESSOA
    @Transactional
    public PessoaResponse atualizar(Long id, PessoaRequest dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id));

        if (dto.responsavelId() != null) {
            Responsavel responsavel = responsavelRepository.findById(dto.responsavelId())
                    .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado com o ID: " + dto.responsavelId()));
            pessoa.setResponsavel(responsavel);
        } else {
            pessoa.setResponsavel(null);
        }

        if (dto.endereco() != null) {
            Endereco endereco = Endereco.builder()
                    .cep(dto.endereco().cep())
                    .logradouro(dto.endereco().logradouro())
                    .bairro(dto.endereco().bairro())
                    .cidade(dto.endereco().cidade())
                    .estado(dto.endereco().estado())
                    .numero(dto.endereco().numero())
                    .complemento(dto.endereco().complemento())
                    .build();
            pessoa.setEndereco(endereco);
        } else {
            pessoa.setEndereco(null);
        }

        pessoa.setNome(dto.nome());
        pessoa.setMatricula(dto.matricula());
        pessoa.setEmail(dto.email());
        pessoa.setCpf(dto.cpf());
        pessoa.setTelefone(dto.telefone());
        pessoa.setDataNascimento(dto.dataNascimento());

        return PessoaResponse.fromEntity(pessoaRepository.save(pessoa));
    }

    // [ DELETE ] - EXCLUI A PESSOA POR ID
    @Transactional
    public void excluir(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pessoa não encontrada com o ID: " + id);
        }
        pessoaRepository.deleteById(id);
    }
}