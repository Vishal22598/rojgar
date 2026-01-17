package com.rojgar.notificationService.service;

import org.springframework.stereotype.Service;

import com.rojgar.notificationService.dto.NotificationRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationService {
	public void sendEmail(NotificationRequest request) {
		log.info("📧 Sending Email Notification");
		log.info("To     : {}", request.getToEmail());
		log.info("Subject: {}", request.getSubject());
		log.info("Message: {}", request.getMessage());
		
		//FUTURE: SMTP / KAFKA / AWS SES
	}
}
