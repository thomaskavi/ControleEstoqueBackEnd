package com.estoque.lelu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.lelu.model.Fornecedor;
import com.estoque.lelu.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@PostMapping
	public ResponseEntity<Fornecedor> adicionarFornecedor(@RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorSalvo = fornecedorService.salvarFornecedor(fornecedor);
		return ResponseEntity.ok(fornecedorSalvo);
	}

	@GetMapping
	public ResponseEntity<List<Fornecedor>> listarFornecedores() {
		List<Fornecedor> fornecedores = fornecedorService.listarFornecedores();
		return ResponseEntity.ok(fornecedores);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fornecedor> buscarFornecedor(@PathVariable Long id) {
		Optional<Fornecedor> fornecedor = fornecedorService.buscarFornecedorPorId(id);
		return fornecedor.isPresent() ? ResponseEntity.ok(fornecedor.get()) : ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Long id,
			@RequestBody Fornecedor fornecedorAtualizado) {
		Optional<Fornecedor> fornecedorExistente = fornecedorService.buscarFornecedorPorId(id);
		if (!fornecedorExistente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		fornecedorAtualizado.setId(id); // Garantir que o ID não seja alterado
		Fornecedor fornecedorSalvo = fornecedorService.salvarFornecedor(fornecedorAtualizado);
		return ResponseEntity.ok(fornecedorSalvo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
		if (fornecedorService.deletarFornecedor(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
