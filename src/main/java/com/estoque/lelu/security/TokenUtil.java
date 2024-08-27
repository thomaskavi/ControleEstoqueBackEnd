package com.estoque.lelu.security;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.estoque.lelu.model.Role;
import com.estoque.lelu.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtil {

    // CONSTANTES UTILITÁRIAS
    public static final String EMISSOR = "LELU";
    public static final String TOKEN_HEADER = "Bearer ";
    private static final String TOKEN_KEY = "01234567890123456789012345678901"; // Deve ser uma chave segura
    private static final long UM_SEGUNDO = 1000;
    private static final long UM_MINUTO = 60 * UM_SEGUNDO;
    private static final long DEZ_MINUTOS = 10 * UM_MINUTO;
    

    public static AuthToken encodeToken(Usuario u) {
        Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String tokenJWT = Jwts.builder()
                .setSubject(u.getLogin())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + DEZ_MINUTOS))
                .claim("roles", u.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .signWith(secretKey)
                .compact();
        return new AuthToken(TOKEN_HEADER + tokenJWT);
    }

    @SuppressWarnings("unchecked")
    public static Authentication decodeToken(String token) {
        try {
            // Remove o prefixo "Bearer " se estiver presente
            if (token.startsWith(TOKEN_HEADER)) {
                token = token.substring(TOKEN_HEADER.length());
            }

            Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();

            String usuario = claims.getSubject();
            String emissor = claims.getIssuer();
            Date validade = claims.getExpiration();
            List<String> roles = claims.get("roles", List.class);

            if (usuario != null && !usuario.isEmpty() && emissor.equals(EMISSOR) && validade.after(new Date())) {
                var authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                return new UsernamePasswordAuthenticationToken(usuario, null, authorities);
            }
        } catch (Exception e) {
            System.out.println("DEBUG - Erro ao decodificar token");
            e.printStackTrace();
        }
        return null;
    }
}
