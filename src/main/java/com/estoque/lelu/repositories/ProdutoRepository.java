package com.estoque.lelu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque.lelu.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
