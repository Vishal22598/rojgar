package com.rojgar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rojgar.dto.LoginRequest;
import com.rojgar.entity.User;
import com.rojgar.repository.UserRepository;
import com.rojgar.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "user registered successfully";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found!"));
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid credentials");
		
		String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
		return ResponseEntity.ok(Map.of("token", token));
	}
	
	
	/*
	 * JWT VALIDATION ENDPOINT (FOR GATEWAY)
	 * Gateway should not decode JWT initially.
	 * Auth-service will validate it.
	 */
	@GetMapping("/validate")
	public ResponseEntity<?> validateToken(
			@RequestHeader("Authorization") String authHeader){
		
		String token = authHeader.substring(7);
		
		if(jwtUtil.isTokenExpired(token))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Token Expired");
		
		return ResponseEntity.ok(jwtUtil.extractClaims(token));
	}
}
