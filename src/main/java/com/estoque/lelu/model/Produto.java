package com.estoque.lelu.model;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.estoque.lelu.validation.NonBlank;
import com.estoque.lelu.validation.NotEmptyDouble;
import com.estoque.lelu.validation.NotEmptyInteger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonBlank(message = "Referência não pode estar em branco.")
	private String referencia;

	@NonBlank(message = "Descrição não pode estar em branco.")
	private String descricao;

	@NonBlank(message = "Tamanho não pode estar em branco.")
	private String tamanho;

	@NonBlank(message = "Cor não pode estar em branco.")
	private String cor;

	@NotEmptyInteger(message = "Unidade não pode estar em branco.")
	@Min(value = 0, message = "Unidade deve ser maior que 0.")
	private Integer unidades;

	@NotEmptyDouble(message = "Preço a vista não pode estar em branco.")
	@Min(value = 0, message = "Preço a vista deve ser maior ou igual a 0.")
	private Double precoAVista;

	@NotEmptyDouble(message = "Preço parcelado não pode estar em branco.")
	@Min(value = 0, message = "Preço parcelado deve ser maior ou igual a 0.")
	private Double precoParcelado;

	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;

	@PostConstruct
	private void trimFields() {
		this.referencia = this.referencia != null ? this.referencia.trim() : null;
		this.descricao = this.descricao != null ? this.descricao.trim() : null;
		this.tamanho = this.tamanho != null ? this.tamanho.trim() : null;
		this.cor = this.cor != null ? this.cor.trim() : null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
