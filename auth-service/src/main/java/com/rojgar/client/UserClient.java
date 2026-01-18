package com.rojgar.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.rojgar.dto.RegisterRequest;
@FeignClient(name = "user-service")
public interface UserClient {
	@PostMapping("/users")
    void createUserProfile(RegisterRequest request);
}
