package com.estoque.lelu.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.lelu.model.Produto;
import com.estoque.lelu.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> produtos = produtoService.listarProdutos();
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarProdutoPorId(@PathVariable Long id) {
		Produto produto = produtoService.buscarProdutoPorId(id);
		if (produto != null) {
			return ResponseEntity.ok(produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> adicionarProduto(@Valid @RequestBody Produto produto, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(error -> {
				if (error instanceof FieldError) {
					return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
				} else {
					return error.getObjectName() + ": " + error.getDefaultMessage();
				}
			}).collect(Collectors.toList());
			return ResponseEntity.badRequest().body(errors);
		}
		try {
			Produto produtoSalvo = produtoService.salvarProduto(produto);
			return ResponseEntity.ok(produtoSalvo);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produto,
			BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(error -> {
				if (error instanceof FieldError) {
					return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
				} else {
					return error.getObjectName() + ": " + error.getDefaultMessage();
				}
			}).collect(Collectors.toList());
			return ResponseEntity.badRequest().body(errors);
		}
		try {
			Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
			return ResponseEntity.ok(produtoAtualizado);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
		try {
			produtoService.deletarProduto(id);
			return ResponseEntity.noContent().build(); // Retorna 204 No Content
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build(); // Retorna 404 Not Found
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
