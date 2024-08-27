package com.estoque.lelu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.estoque.lelu.model.Usuario;
import com.estoque.lelu.repositories.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			Usuario usuario = usuarioRepository.findByLogin(username); // Certifique-se de que o método existe
			if (usuario != null) {
				return org.springframework.security.core.userdetails.User.withUsername(usuario.getLogin())
						.password(usuario.getSenha())
						.authorities(usuario.getRoles().stream().map(role -> "ROLE_" + role.getName()) // Adiciona o
																										// prefixo
																										// "ROLE_"
								.toArray(String[]::new))
						.build();
			}
			throw new UsernameNotFoundException("Usuário não encontrado");
		};
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors() // Adiciona suporte a CORS
            .and()
            .authorizeHttpRequests()
            .antMatchers("/free").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers(HttpMethod.DELETE, "/api/fornecedores/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/produtos").hasAnyRole("ADMIN", "GERENTE")
            .antMatchers(HttpMethod.GET, "/api/produtos/**").hasAnyRole("USER", "ADMIN", "GERENTE")
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
