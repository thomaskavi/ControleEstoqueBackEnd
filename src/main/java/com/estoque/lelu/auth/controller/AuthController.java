package com.estoque.lelu.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.estoque.lelu.model.Usuario;
import com.estoque.lelu.security.AuthToken;
import com.estoque.lelu.security.TokenUtil;

@RestController
public class AuthController {

	@GetMapping("/free")
	public String sayFreeHello() {
		return "Este é um endpoint liberado para nossa API";
	}

	@GetMapping("/auth")
	public String sayAuthHello() {
		return "Este é um endpoint que precisa de autenticação";
	}	

	@PostMapping("/login")
	public ResponseEntity<AuthToken> fazerLogin(@RequestBody Usuario u) {
		if (u.getLogin().equals("thomaskavi") && u.getSenha().equals("kavithomas")) {
			return ResponseEntity.ok((TokenUtil.encodeToken(u)));
		}
		return ResponseEntity.status(403).build();
	}

}
