package com.rojgar.filter;


import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.rojgar.security.JwtUtil;

import reactor.core.publisher.Mono;
@Component
public class JwtFilter implements GlobalFilter, Ordered{
	private final JwtUtil jwtUtil;
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(JwtFilter.class);
	
	public JwtFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		log.info("Incoming request: {} {}", 
				exchange.getRequest().getMethod(),
				exchange.getRequest().getURI());
		String path = exchange.getRequest().getURI().getPath();
		
		//public endpoints
		if(path.contains("/auth/login") || path.startsWith("/auth/register"))
			return chain.filter(exchange);
		
		String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		
		String token = authHeader.substring(7);
		
		try {
			jwtUtil.validateToken(token);
			String email = jwtUtil.extractUsername(token);
			String role = jwtUtil.extractRole(token);
			
			//Mutate request and add headers
			ServerWebExchange mutateExchange = exchange.mutate().request(builder -> builder
					.header("X-User-Email", email)
					.header("X-User-Role", role)
				).build();
			
			return chain.filter(mutateExchange);
			
		}catch(Exception e) {
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		
	}
	

	@Override
	public int getOrder() {
		return -1; //execute before routing
	}
}
