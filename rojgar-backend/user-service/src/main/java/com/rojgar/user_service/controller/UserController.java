package com.rojgar.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rojgar.user_service.entity.User;
import com.rojgar.user_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/me")
	public ResponseEntity<?> getLoggedInUser(
			@AuthenticationPrincipal Jwt jwt){
		
		String email = jwt.getClaim("sub");
		User user = userService.getByEmail(email);
		System.out.println("user: " + user);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public User create(@RequestBody User userr) {
		return userService.createUser(userr);
	}
	
	@GetMapping("/email/{email}")
	public User getByEmail(@PathVariable String email) {
		return userService.getByEmail(email);
	}
}
