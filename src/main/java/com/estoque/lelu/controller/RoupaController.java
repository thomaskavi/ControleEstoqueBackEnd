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

import com.estoque.lelu.model.Roupa;
import com.estoque.lelu.service.RoupaService;

@RestController
@RequestMapping("/api/roupas")
public class RoupaController {

	@Autowired
	private RoupaService roupaService;

	@GetMapping
	public List<Roupa> listarRoupas() {
		return roupaService.listarRoupas();
	}

	@PostMapping
	public ResponseEntity<?> adicionarRoupa(@Valid @RequestBody Roupa roupa, BindingResult result) {
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
		Roupa roupaSalva = roupaService.salvarRoupa(roupa);
		return ResponseEntity.ok(roupaSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarRoupa(@PathVariable Long id) {
	    Roupa roupa = roupaService.buscarRoupaPorId(id);
	    if (roupa == null) {
	        // Mensagem de erro detalhada
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Roupa com ID " + id + " não encontrada.");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	    return ResponseEntity.ok(roupa);
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarRoupa(@PathVariable Long id, @Valid @RequestBody Roupa roupa,
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
		Roupa roupaAtualizada = roupaService.atualizarRoupa(id, roupa);
		if (roupaAtualizada == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(roupaAtualizada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarRoupa(@PathVariable Long id) {
		roupaService.deletarRoupa(id);
		return ResponseEntity.noContent().build();
	}
}
