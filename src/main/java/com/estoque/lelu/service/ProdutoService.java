package com.estoque.lelu.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.lelu.model.Produto;
import com.estoque.lelu.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto buscarProdutoPorId(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}

	public void deletarProduto(Long id) {
		produtoRepository.deleteById(id);
	}

	public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
		Produto produtoExistente = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrada"));

		// Atualiza apenas os campos fornecidos
		if (produtoAtualizado.getReferencia() != null) {
			produtoExistente.setReferencia(produtoAtualizado.getReferencia());
		}
		if (produtoAtualizado.getDescricao() != null) {
			produtoExistente.setDescricao(produtoAtualizado.getDescricao());
		}
		if (produtoAtualizado.getTamanho() != null) {
			produtoExistente.setTamanho(produtoAtualizado.getTamanho());
		}
		if (produtoAtualizado.getCor() != null) {
			produtoExistente.setCor(produtoAtualizado.getCor());
		}
		if (produtoAtualizado.getUnidades() != null) { // Usando Integer
			produtoExistente.setUnidades(produtoAtualizado.getUnidades());
		}
		if (produtoAtualizado.getPrecoAVista() != null) { // Usando Double
			produtoExistente.setPrecoAVista(produtoAtualizado.getPrecoAVista());
		}
		if (produtoAtualizado.getPrecoParcelado() != null) { // Usando Double
			produtoExistente.setPrecoParcelado(produtoAtualizado.getPrecoParcelado());
		}

		// Salvar a produto atualizada
		return produtoRepository.save(produtoExistente);
	}
}
