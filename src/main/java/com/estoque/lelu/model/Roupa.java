package com.estoque.lelu.model;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import com.estoque.lelu.validation.NonBlank;
import com.estoque.lelu.validation.NotEmptyDouble;
import com.estoque.lelu.validation.NotEmptyInteger;

@Entity
public class Roupa {

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
	@Min(value = 0, message = "Unidade deve ser maior ou igual a 0.")
	private Integer unidades;

	@NotEmptyDouble(message = "Preço a vista não pode estar em branco.")
	@Min(value = 0, message = "Preço a vista deve ser maior ou igual a 0.")
	private Double precoAVista;

	@NotEmptyDouble(message = "Preço parcelado não pode estar em branco.")
	@Min(value = 0, message = "Preço parcelado deve ser maior ou igual a 0.")
	private Double precoParcelado;

	private String contatoFornecedor;

	@PostConstruct
	private void trimFields() {
		this.referencia = this.referencia != null ? this.referencia.trim() : null;
		this.descricao = this.descricao != null ? this.descricao.trim() : null;
		this.tamanho = this.tamanho != null ? this.tamanho.trim() : null;
		this.cor = this.cor != null ? this.cor.trim() : null;
		this.contatoFornecedor = this.contatoFornecedor != null ? this.contatoFornecedor.trim() : null;
	}

	public String getContatoFornecedor() {
		return contatoFornecedor;
	}

	public void setContatoFornecedor(String contatoFornecedor) {
		this.contatoFornecedor = contatoFornecedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Double getPrecoAVista() {
		return precoAVista;
	}

	public void setPrecoAVista(Double precoAVista) {
		this.precoAVista = precoAVista;
	}

	public Double getPrecoParcelado() {
		return precoParcelado;
	}

	public void setPrecoParcelado(Double precoParcelado) {
		this.precoParcelado = precoParcelado;
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
		Roupa other = (Roupa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
