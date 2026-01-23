package com.rojgar.user_service.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	private static final String SECRET_KEY = "my-super-secret-key-which-is-very-long";
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/me").authenticated()
                .anyRequest().permitAll()
            )
            .oauth2ResourceServer(oauth ->
            	oauth.jwt(jwt ->
                	jwt.decoder(jwtDecoder())
            			)
            );

        return http.build();
    }
    
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
            new SecretKeySpec(
                SECRET_KEY.getBytes(),
                "HmacSHA256"
            )
        ).build();
    }
}
