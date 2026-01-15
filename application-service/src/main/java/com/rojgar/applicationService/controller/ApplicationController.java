package com.rojgar.applicationService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rojgar.applicationService.entity.JobApplication;
import com.rojgar.applicationService.service.ApplicationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {
	
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
		
		return ResponseEntity.ok(service.applyForJob(jobId, email));
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
