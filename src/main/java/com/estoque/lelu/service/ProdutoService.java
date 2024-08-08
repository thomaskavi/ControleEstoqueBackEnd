package com.estoque.lelu.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.lelu.model.Fornecedor;
import com.estoque.lelu.model.Produto;
import com.estoque.lelu.repositories.FornecedorRepository;
import com.estoque.lelu.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<Produto> listarProdutos() {
		return produtoRepository.findAll();
	}

	public Produto salvarProduto(Produto produto) {
		if (produto.getFornecedor() == null || produto.getFornecedor().getId() == null) {
			throw new RuntimeException("Fornecedor deve ser fornecido");
		}

		Fornecedor fornecedor = fornecedorRepository.findById(produto.getFornecedor().getId())
				.orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

		produto.setFornecedor(fornecedor);
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
		if (produtoAtualizado.getUnidades() != null) {
			produtoExistente.setUnidades(produtoAtualizado.getUnidades());
		}
		if (produtoAtualizado.getPrecoAVista() != null) {
			produtoExistente.setPrecoAVista(produtoAtualizado.getPrecoAVista());
		}
		if (produtoAtualizado.getPrecoParcelado() != null) {
			produtoExistente.setPrecoParcelado(produtoAtualizado.getPrecoParcelado());
		}
		if (produtoAtualizado.getFornecedor() != null && produtoAtualizado.getFornecedor().getId() != null) {
			Fornecedor fornecedor = fornecedorRepository.findById(produtoAtualizado.getFornecedor().getId())
					.orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
			produtoExistente.setFornecedor(fornecedor);
		}

		return produtoRepository.save(produtoExistente);
	}
}
