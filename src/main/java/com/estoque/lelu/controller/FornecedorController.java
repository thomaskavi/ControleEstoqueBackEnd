package com.estoque.lelu.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.estoque.lelu.model.Fornecedor;
import com.estoque.lelu.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<?> adicionarFornecedor(@Valid @RequestBody Fornecedor fornecedor, BindingResult result) {
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
    public ResponseEntity<?> atualizarFornecedor(@PathVariable Long id,
            @Valid @RequestBody Fornecedor fornecedorAtualizado, BindingResult result) {
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
