package com.estoque.lelu.service;

import java.util.List;
import java.util.Optional;

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

	public Optional<Fornecedor> buscarFornecedorPorId(Long id) {
		return fornecedorRepository.findById(id);
	}

	public boolean deletarFornecedor(Long id) {
		if (fornecedorRepository.existsById(id)) {
			fornecedorRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Fornecedor> listarFornecedores() {
		return fornecedorRepository.findAll();
	}
}
