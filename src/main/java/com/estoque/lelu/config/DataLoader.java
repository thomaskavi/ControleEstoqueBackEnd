package com.estoque.lelu.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estoque.lelu.model.Role;
import com.estoque.lelu.model.Usuario;
import com.estoque.lelu.repositories.RoleRepository;
import com.estoque.lelu.repositories.UsuarioRepository;

@Configuration
public class DataLoader {

	@Bean
	CommandLineRunner initData(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
		return args -> {
			Role adminRole = new Role(null, "ROLE_ADMIN");
			Role gerenteRole = new Role(null, "ROLE_GERENTE");
			Role userRole = new Role(null, "ROLE_USER");

			roleRepository.saveAll(Set.of(adminRole, gerenteRole, userRole));

			Usuario admin = new Usuario(null, "admin", "{noop}admin", Set.of(adminRole));
			Usuario gerente = new Usuario(null, "gerente", "{noop}gerente", Set.of(gerenteRole));
			Usuario user = new Usuario(null, "user", "{noop}user", Set.of(userRole));

			usuarioRepository.saveAll(Set.of(admin, gerente, user));
		};
	}

}