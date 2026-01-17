package com.rojgar.applicationService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rojgar.applicationService.dto.NotificationRequest;

@FeignClient(name = "notification-service")
public interface NotificationClient {
	@PostMapping("notifications/email")
	void sendNotification(@RequestBody NotificationRequest request);
}
