package com.rojgar.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {
	private static final String SECRET = "my-super-secret-key-which-is-very-long";
	private static final long EXPIRATION = 1000 * 60 * 60;
	
	private Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(String email, String role) {
		return Jwts.builder()
				.setSubject(email)
				.claim("role", role)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Claims extractClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}
	
	public String extractRole(String token) {
	    return extractClaims(token).get("role", String.class);
	}
	
	public boolean isTokenValid(String token) {
		try {
			extractClaims(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}
	
}
