package com.rojgar.notificationService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rojgar.notificationService.dto.NotificationRequest;
import com.rojgar.notificationService.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notifications")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService service;
	
	@PostMapping("/email")
	public ResponseEntity<Void> notify(@RequestBody NotificationRequest request){
		service.sendEmail(request);
		log.info("in Notification controller");

        return ResponseEntity.ok().build();
	}
}
