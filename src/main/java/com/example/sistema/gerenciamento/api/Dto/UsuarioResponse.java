package com.example.sistema.gerenciamento.api.Dto;

import com.example.sistema.gerenciamento.api.Entity.Usuario;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nomeCompleto,
        String email,
        String telefone,
        String cpf,
        String fotoUrl,
        String tokenConvite,
        LocalDate dataNascimento,
        boolean ativo,
        LocalDateTime dataCadastro
) {
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getFotoUrl(),
                usuario.getTokenConvite(),
                usuario.getDataNascimento(),
                usuario.isAtivo(),
                usuario.getDataCadastro()
        );
    }
}