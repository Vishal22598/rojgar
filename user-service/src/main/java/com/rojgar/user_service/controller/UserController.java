package com.rojgar.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping
	public User create(@RequestBody User userr) {
		return userService.createUser(userr);
	}
	
	@GetMapping("/email/{email}")
	public User getByEmail(@PathVariable String email) {
		return userService.getByEmail(email);
	}
}
