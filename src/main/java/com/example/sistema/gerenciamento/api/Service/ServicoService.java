package com.example.sistema.gerenciamento.api.Service;

import com.example.sistema.gerenciamento.api.Dto.ServicoRequest;
import com.example.sistema.gerenciamento.api.Dto.ServicoResponse;
import com.example.sistema.gerenciamento.api.Entity.Responsavel;
import com.example.sistema.gerenciamento.api.Entity.Servico;
import com.example.sistema.gerenciamento.api.Exception.ResourceNotFoundException;
import com.example.sistema.gerenciamento.api.Repository.ResponsavelRepository;
import com.example.sistema.gerenciamento.api.Repository.ServicoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ResponsavelRepository responsavelRepository;

    // [ GET ] - RETORNA TODOS OS SERVIÇOS CADASTRADOS
    @Transactional(readOnly = true)
    public List<ServicoResponse> listarTodos() {
        return servicoRepository.findAll().stream()
                .map(ServicoResponse::fromEntity)
                .toList();
    }

    // [ GET ] - RETORNA APENAS OS SERVIÇOS COM STATUS ATIVO
    @Transactional(readOnly = true)
    public List<ServicoResponse> listarAtivos() {
        return servicoRepository.findByAtivoTrue().stream()
                .map(ServicoResponse::fromEntity)
                .toList();
    }

    // [ GET ] - BUSCA UM SERVIÇO PELO ID OU LANÇA EXCEÇÃO CASO NÃO ENCONTRE
    @Transactional(readOnly = true)
    public ServicoResponse buscarPorId(Long id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + id));
        return ServicoResponse.fromEntity(servico);
    }

    // [ POST ] - VALIDA O RESPONSÁVEL E CADASTRA UM NOVO SERVIÇO
    @Transactional
    public ServicoResponse salvar(ServicoRequest dto) {
        Responsavel responsavel = responsavelRepository.findById(dto.responsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado com o ID: " + dto.responsavelId()));

        Servico servico = Servico.builder()
                .nome(dto.nome())
                .categoria(dto.categoria())
                .descricao(dto.descricao())
                .duracaoMinutos(dto.duracaoMinutos())
                .ativo(dto.ativo() != null ? dto.ativo() : true)
                .responsavel(responsavel)
                .build();

        return ServicoResponse.fromEntity(servicoRepository.save(servico));
    }

    // [ PUT ] - ATUALIZA OS DADOS DE UM SERVIÇO EXISTENTE
    @Transactional
    public ServicoResponse atualizar(Long id, ServicoRequest dto) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + id));

        Responsavel responsavel = responsavelRepository.findById(dto.responsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado com o ID: " + dto.responsavelId()));

        servico.setNome(dto.nome());
        servico.setCategoria(dto.categoria());
        servico.setDescricao(dto.descricao());
        servico.setDuracaoMinutos(dto.duracaoMinutos());
        if (dto.ativo() != null) {
            servico.setAtivo(dto.ativo());
        }
        servico.setResponsavel(responsavel);

        return ServicoResponse.fromEntity(servicoRepository.save(servico));
    }

    // [ DELETE ] - REMOVE UM SERVIÇO DO BANCO DE DADOS PELO ID
    @Transactional
    public void excluir(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Serviço não encontrado com o ID: " + id);
        }
        servicoRepository.deleteById(id);
    }
}