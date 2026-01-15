package com.rojgar.user_service.service;

import org.springframework.stereotype.Service;

import com.rojgar.user_service.entity.User;
import com.rojgar.user_service.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found!"));
	}
	
}
