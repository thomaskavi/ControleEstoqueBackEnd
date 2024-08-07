package com.estoque.lelu.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.estoque.lelu.validation.NonBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonBlank(message = "Nome não pode ser vazio")
	private String nome;

	private String contato;

	@Email(message = "Email inválido")
	private String email;

	@NonBlank(message = "Telefone não pode estar vazio")
	private String telefone;

	@NonBlank(message = "Localização não pode ser vazia")
	private String localizacao;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(id, other.id);
	}

}
