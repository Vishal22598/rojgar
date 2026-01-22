package com.rojgar.applicationService.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rojgar.applicationService.dto.NotificationRequest;
import com.rojgar.applicationService.entity.ApplicationStatus;
import com.rojgar.applicationService.entity.JobApplication;
import com.rojgar.applicationService.repository.JobApplicationRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {
	@Autowired
	private final RestTemplate restTemplate;
	private final JobApplicationRepository repository;
	
	
	public JobApplication applyForJob(Long jobId, String email) {
		if(repository.existsByJobIdAndApplicationEmail(jobId, email)) {
			System.out.println("jobId: " + jobId + " email: " + email);
			throw new RuntimeException("Already applied for this job");
		}
		
		JobApplication application = new JobApplication();
		application.setJobId(jobId);
		application.setApplicationEmail(email);
		application.setStatus(ApplicationStatus.APPLIED);
		application.setAppliedAt(LocalDateTime.now());
		
		return repository.save(application);
	}
	
	@CircuitBreaker(name = "notificationService", fallbackMethod = "fallback")
	public void sendNotification(NotificationRequest request) {
		restTemplate.postForObject(
				"http://notification-service/notifications/email", 
				request, 
				Void.class
		);
	}
	
	public void fallback(NotificationRequest request, Exception ex) {
		log.warn("Notification service down. Job application still successful.");
	}
	
	public List<JobApplication> getMyApplications(String email){
		return repository.findByApplicationEmail(email);
	}
	
	public List<JobApplication> getApplicationForJob(Long jobId){
		return repository.findByJobId(jobId);
	}
}
