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

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith(TokenUtil.TOKEN_HEADER)) {
            String token = authHeader.substring(TokenUtil.TOKEN_HEADER.length()).trim();
            Authentication auth = TokenUtil.decodeToken(token);

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                // Token é inválido
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                ErroDTO erro = new ErroDTO(401, "Usuário não autorizado para este sistema");
                response.getWriter().print(mapper.writeValueAsString(erro));
                response.getWriter().flush();
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
