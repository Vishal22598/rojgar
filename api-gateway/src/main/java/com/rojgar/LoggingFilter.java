package com.rojgar;

import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;



import reactor.core.publisher.Mono;
/*
 * implemented a GlobalFilter in Spring Cloud Gateway to log and pre-process all incoming requests
 */
@Component
public class LoggingFilter implements GlobalFilter{
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoggingFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("Incoming request: {} {}", 
				exchange.getRequest().getMethod(),
				exchange.getRequest().getURI());
		return chain.filter(exchange);
	}

}
