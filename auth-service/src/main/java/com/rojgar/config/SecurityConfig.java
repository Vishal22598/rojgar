package com.rojgar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rojgar.security.JwtAuthenticationFilter;
import com.rojgar.security.JwtUtil;

@Configuration
public class SecurityConfig {
	
	private final JwtUtil jwtUtil;
	
	public SecurityConfig(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		JwtAuthenticationFilter jwtFilter = 
				new JwtAuthenticationFilter(jwtUtil);
		
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session ->
			/*
			 * We use a stateless JWT-based authentication where the token is validated 
			 * in a custom OncePerRequestFilter and user details are set into the SecurityContext
			 * for request authorization.
			 */
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/auth/login", "/auth/register")
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			.addFilterBefore(jwtFilter,
	                UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
