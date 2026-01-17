package com.rojgar.applicationService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rojgar.applicationService.dto.NotificationRequest;
import com.rojgar.applicationService.entity.JobApplication;
import com.rojgar.applicationService.service.ApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {
//	@Autowired
//	private final RestTemplate restTemplate;
	private final ApplicationService service;
	
	//JOB_SEEKER
	@PostMapping("/{jobId}")
	public ResponseEntity<?> apply(
			@PathVariable Long jobId,
			@RequestHeader("X-User-Email") String email,
			@RequestHeader("X-User-Role") String role){
		if(!role.equals("JOB_SEEKER")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Only job seeker can apply");
		}
		
		service.applyForJob(jobId, email);
		
		NotificationRequest notification = new NotificationRequest(
				email,
				"Job Application Submitted",
				"You applied for Job Id: " + jobId
		);
		
		service.sendNotification(notification);
//		restTemplate.postForObject(
//				"http://notification-service/notifications/email", 
//				notification, 
//				Void.class);
		
		return ResponseEntity.ok("Application Submitted");
	}
	
	
	//JOB_SEEKER
	@GetMapping("/job/{jobId}")
	public List<JobApplication> myApplications(
			@RequestHeader("X-User-Email") String email){
		return service.getMyApplications(email);
	}
	
	//EMPLOYER
	
	public ResponseEntity<?> jobApplicatioins(
			@PathVariable Long JobId,
			@RequestHeader("X-User-Role") String role){
		
		if (!role.equals("EMPLOYER")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Access denied");
		}
		
		return ResponseEntity.ok(service.getApplicationForJob(JobId));
	}
}
