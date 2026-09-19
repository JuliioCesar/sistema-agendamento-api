package com.example.sistema.gerenciamento.api.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Dados de endereco persistidos junto com a pessoa. */
// @Embeddable indica que esta classe nao possui tabela propria.
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

	/** Rua ou avenida do endereco. */
	private String logradouro;
	/** Numero do imovel. */
	private String numero;
	/** Complemento opcional. */
	private String complemento;
	/** Bairro do endereco. */
	private String bairro;
	/** Cidade do endereco. */
	private String cidade;
	/** Unidade federativa. */
	private String estado;
	/** Codigo postal. */
	private String cep;
}