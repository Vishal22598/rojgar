package com.rojgar.applicationService.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rojgar.applicationService.entity.ApplicationStatus;
import com.rojgar.applicationService.entity.JobApplication;
import com.rojgar.applicationService.repository.JobApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {
	private final JobApplicationRepository repository;
	
	public JobApplication applyForJob(Long jobId, String email) {
		if(repository.existByJobIdAndApplicationEmail(jobId, email)) {
			throw new RuntimeException("Already applied for this job");
		}
		
		JobApplication application = new JobApplication();
		application.setJobId(jobId);
		application.setApplicationEmail(email);
		application.setStatus(ApplicationStatus.APPLIED);
		application.setAppliedAt(LocalDateTime.now());
		
		return repository.save(application);
	}
	
	public List<JobApplication> getMyApplications(String email){
		return repository.findByApplicationEmail(email);
	}
	
	public List<JobApplication> getApplicationForJob(Long jobId){
		return repository.findByJobId(jobId);
	}
}
