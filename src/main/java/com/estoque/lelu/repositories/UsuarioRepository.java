package com.estoque.lelu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque.lelu.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
