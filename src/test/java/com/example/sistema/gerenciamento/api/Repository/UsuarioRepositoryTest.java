package com.example.sistema.gerenciamento.api.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.sistema.gerenciamento.api.Entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

/** Testes de integracao do repositorio de usuarios usando um banco H2 isolado. */
@DataJpaTest
class UsuarioRepositoryTest {

    /** Repositorio real criado pelo Spring Data para o teste. */
    @Autowired
    private UsuarioRepository repository;

    /** Valida busca por email e as verificacoes de duplicidade de email e CPF. */
    @Test
    void deveBuscarUsuarioEValidarDuplicidades() {
        // Arrange: prepara um operador com email e CPF unicos.
        Usuario usuario = Usuario.builder()
                .nomeCompleto("Maria Operadora")
                .email("maria@exemplo.com")
                .senha("senha-segura")
                .cpf("12345678900")
                .build();
        repository.save(usuario);

        // Act: executa as derived queries definidas no repositorio.
        var encontrado = repository.findByEmail("maria@exemplo.com");

        // Assert: confirma o resultado e as flags de existencia.
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNomeCompleto()).isEqualTo("Maria Operadora");
        assertThat(repository.existsByEmail("maria@exemplo.com")).isTrue();
        assertThat(repository.existsByCpf("12345678900")).isTrue();
    }

    /** Confirma que a restricao unique do email e aplicada pelo banco. */
    @Test
    void deveImpedirEmailDuplicado() {
        // Arrange: persiste o primeiro usuario com o email que sera repetido.
        repository.saveAndFlush(Usuario.builder()
                .nomeCompleto("Primeiro Usuario")
                .email("duplicado@exemplo.com")
                .senha("senha")
                .build());

        // Act e Assert: o segundo insert viola a chave unica da coluna email.
        assertThrows(DataIntegrityViolationException.class, () -> repository.saveAndFlush(
                Usuario.builder()
                        .nomeCompleto("Segundo Usuario")
                        .email("duplicado@exemplo.com")
                        .senha("senha")
                        .build()));
    }
}
