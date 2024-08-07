package com.estoque.lelu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.lelu.model.Fornecedor;
import com.estoque.lelu.repositories.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public Fornecedor salvarFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}

	public Fornecedor buscarFornecedorPorId(Long id) {
		return fornecedorRepository.findById(id).orElse(null);
	}

	public void deletarFornecedor(Long id) {
		fornecedorRepository.deleteById(id);
	}

	public List<Fornecedor> listarFornecedores() {
		return fornecedorRepository.findAll();
	}
}
