package com.example.sistema.gerenciamento.api.Service;

import com.example.sistema.gerenciamento.api.Dto.ResponsavelRequest;
import com.example.sistema.gerenciamento.api.Dto.ResponsavelResponse;
import com.example.sistema.gerenciamento.api.Entity.Responsavel;
import com.example.sistema.gerenciamento.api.Exception.ResourceNotFoundException;
import com.example.sistema.gerenciamento.api.Repository.ResponsavelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;

    // [ GET ] - RETORNA TODOS OS RESPONSÁVEIS
    @Transactional(readOnly = true)
    public List<ResponsavelResponse> listarTodos() {
        return responsavelRepository.findAll().stream()
                .map(ResponsavelResponse::fromEntity)
                .toList();
    }

    // [ GET ] - BUSCA RESPONSÁVEL POR ID
    @Transactional(readOnly = true)
    public ResponsavelResponse buscarPorId(Long id) {
        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado com o ID: " + id));
        return ResponsavelResponse.fromEntity(responsavel);
    }

    // [ GET ] - PESQUISA RESPONSÁVEIS POR NOME OU E-MAIL
    @Transactional(readOnly = true)
    public List<ResponsavelResponse> pesquisar(String termo) {
        return responsavelRepository.findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(termo, termo).stream()
                .map(ResponsavelResponse::fromEntity)
                .toList();
    }

    // [ POST ] - CADASTRA UM NOVO RESPONSÁVEL
    @Transactional
    public ResponsavelResponse salvar(ResponsavelRequest dto) {
        if (responsavelRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Já existe um responsável cadastrado com o e-mail: " + dto.email());
        }

        Responsavel responsavel = Responsavel.builder()
                .nome(dto.nome())
                .email(dto.email())
                .telefone(dto.telefone())
                .build();

        return ResponsavelResponse.fromEntity(responsavelRepository.save(responsavel));
    }

    // [ PUT ] - ATUALIZA DADOS DO RESPONSÁVEL
    @Transactional
    public ResponsavelResponse atualizar(Long id, ResponsavelRequest dto) {
        Responsavel responsavel = responsavelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado com o ID: " + id));

        if (!responsavel.getEmail().equalsIgnoreCase(dto.email()) && responsavelRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Já existe um responsável cadastrado com o e-mail: " + dto.email());
        }

        responsavel.setNome(dto.nome());
        responsavel.setEmail(dto.email());
        responsavel.setTelefone(dto.telefone());

        return ResponsavelResponse.fromEntity(responsavelRepository.save(responsavel));
    }

    // [ DELETE ] - REMOVE UM RESPONSÁVEL POR ID
    @Transactional
    public void excluir(Long id) {
        if (!responsavelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Responsável não encontrado com o ID: " + id);
        }
        responsavelRepository.deleteById(id);
    }
}