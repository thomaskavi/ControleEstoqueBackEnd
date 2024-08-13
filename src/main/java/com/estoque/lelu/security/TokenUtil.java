package com.estoque.lelu.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class TokenUtil {

	public static Authentication decodeToken(HttpServletRequest request) {
		if (request.getHeader("Authorization").equals("Bearer 10203040")) {
			return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
		}
		return null;
	}
}
