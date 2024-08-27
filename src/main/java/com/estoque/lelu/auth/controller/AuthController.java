package com.estoque.lelu.auth.controller;

import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.lelu.model.Role;
import com.estoque.lelu.model.Usuario;
import com.estoque.lelu.repositories.RoleRepository;
import com.estoque.lelu.security.AuthToken;
import com.estoque.lelu.security.TokenUtil;

@RestController
public class AuthController {

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping("/free")
	public String sayFreeHello() {
		return "Este é um endpoint liberado para nossa API";
	}

	@GetMapping("/auth")
	public String sayAuthHello() {
		return "Este é um endpoint que precisa de autenticação";
	}

	@PostMapping("/login")
	public ResponseEntity<Object> fazerLogin(@RequestBody Usuario u) {
	    System.out.println("Tentando login com: " + u.getLogin());

	    // Verificação de login e senha
	    if ("admin".equals(u.getLogin()) && "admin".equals(u.getSenha())) {
	        // Encontrar o papel ADMIN no banco de dados
	        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

	        if (adminRole == null) {
	            return ResponseEntity.status(500).body("Role ADMIN não encontrada");
	        }

	        // Criar um conjunto com o papel ADMIN
	        Set<Role> roles = new HashSet<>();
	        roles.add(adminRole);

	        // Criar um novo usuário com o papel encontrado
	        Usuario loggedInUser = new Usuario(null, u.getLogin(), u.getSenha(), roles);

	        AuthToken token = TokenUtil.encodeToken(loggedInUser);
	        System.out.println("Token gerado: " + token.getToken());
	        return ResponseEntity.ok(token);
	    }

	    System.out.println("Login ou senha incorretos");
	    return ResponseEntity.status(403).body("Login ou senha incorretos");
	}


}