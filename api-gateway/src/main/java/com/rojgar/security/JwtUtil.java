package com.rojgar.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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
}
