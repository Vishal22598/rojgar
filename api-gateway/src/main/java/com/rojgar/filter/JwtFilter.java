package com.rojgar.filter;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import com.rojgar.security.JwtUtil;

import reactor.core.publisher.Mono;

public class JwtFilter implements GlobalFilter, Ordered{
	private final JwtUtil jwtUtil;
	
	public JwtFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		String path = exchange.getRequest().getURI().getPath();
		
		//public endpoints
		if(path.contains("/auth/login") || path.contains("/auth/register"))
			return chain.filter(exchange);
		
		String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		
		String token = authHeader.substring(7);
		
		try {
			jwtUtil.validateToken(token);
		}catch(Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}
	

	@Override
	public int getOrder() {
		return -1; //execute before routing
	}
}
