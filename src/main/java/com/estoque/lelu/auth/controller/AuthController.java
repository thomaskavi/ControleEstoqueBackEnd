package com.estoque.lelu.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
