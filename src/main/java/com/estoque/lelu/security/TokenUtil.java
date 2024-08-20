package com.estoque.lelu.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.estoque.lelu.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtil {

	// CONSTANTES UTILITÁRIAS
	private static final String EMISSOR = "LELU";
	private static final String TOKEN_HEADER = "Bearer ";
	private static final String TOKEN_KEY = "01234567890123456789012345678901"; // Deve ser uma chave segura
	private static final long UM_SEGUNDO = 1000;
	private static final long UM_MINUTO = 60 * UM_SEGUNDO;

	public static AuthToken encodeToken(Usuario u) {
		Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
		String tokenJWT = Jwts.builder().setSubject(u.getLogin()).setIssuer(EMISSOR)
				.setExpiration(new Date(System.currentTimeMillis() + UM_MINUTO)).signWith(secretKey).compact();
		return new AuthToken(TOKEN_HEADER + tokenJWT);
	}

	public static Authentication decodeToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith(TOKEN_HEADER)) {
			String jwtToken = header.substring(TOKEN_HEADER.length());
			try {
				Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
				Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtToken)
						.getBody();

				String usuario = claims.getSubject();
				String emissor = claims.getIssuer();
				Date validade = claims.getExpiration();

				if (usuario.length() > 0 && emissor.equals(EMISSOR)
						&& validade.after(new Date(System.currentTimeMillis())));
				return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());

			} catch (Exception e) {
				System.out.println("DEBUG - Erro ao decodificar token");
				System.out.println(e.getMessage());
				return null;
			}
		}
		return null;
	}
}
