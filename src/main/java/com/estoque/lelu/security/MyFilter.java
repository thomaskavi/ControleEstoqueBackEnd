package com.estoque.lelu.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain)
			throws ServletException, IOException {

		// Se a rota for "/free", ignora o filtro e segue adiante
		if ("/free".equals(request.getRequestURI())) {
			filterChain.doFilter(request, response);
			return;
		}

		if (request.getHeader("Authorization") != null) {
			// RECUPERO O CABEÇALHO
			Authentication auth = TokenUtil.decodeToken(request);
			// CABEÇALHO EXISTE, PRECISO VALIDAR
			if (auth != null) {
				// SE TOKEN = VÁLIDO MANDA A REQUISIÇÃO PARA FRENTE
				SecurityContextHolder.getContext().setAuthentication(auth);
			} else {
				// TOKEN EXISTE MAS NÃO É VÁLIDO (TRATAR ERRO 403 COM MENSAGEM)
				System.out.println("Erro no TOKEN");
				ErroDTO erro = new ErroDTO(401, "Usuario nao autorizado para este sistema");
				response.setStatus(erro.getStatus());
				response.setContentType("application/json");
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().print(mapper.writeValueAsString(erro));
				response.getWriter().flush();
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
