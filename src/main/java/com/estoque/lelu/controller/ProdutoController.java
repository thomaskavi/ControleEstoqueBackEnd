package com.estoque.lelu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public List<Produto> listarProduto() {
		return produtoService.listarProdutos();
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
		Produto roupaSalva = produtoService.salvarProduto(produto);
		return ResponseEntity.ok(roupaSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarProduto(@PathVariable Long id) {
	    Produto produto = produtoService.buscarProdutoPorId(id);
	    if (produto == null) {
	        // Mensagem de erro detalhada
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Produto com ID " + id + " não encontrada.");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	    return ResponseEntity.ok(produto);
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
		Produto roupaAtualizada = produtoService.atualizarProduto(id, produto);
		if (roupaAtualizada == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(roupaAtualizada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
		produtoService.deletarProduto(id);
		return ResponseEntity.noContent().build();
	}
}
