package com.rojgar.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final String SECRET = "my-super-secret-key-which-is-very-long";
	
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	}
	
	public void validateToken(String token) {
		Jwts.parserBuilder()
		.setSigningKey(getSignKey())
		.build()
		.parseClaimsJws(token);
	}
	
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}
	
	public String extractRole(String token) {
		return getClaims(token).get("role", String.class);
	}
}
