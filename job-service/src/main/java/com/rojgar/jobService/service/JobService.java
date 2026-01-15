package com.rojgar.jobService.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rojgar.jobService.entity.Job;
import com.rojgar.jobService.repository.JobRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {
	private final JobRepository jobRepository;
	
	public Job createJob(Job job, String employerEmail) {
		job.setPostedAt(LocalDateTime.now());
		job.setPostedBy(employerEmail);
		return jobRepository.save(job);
	}
	
	public List<Job> getAllJobs(){
		return jobRepository.findAll();
	}
	
	public Job getJobById(Long id) {
		return jobRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Job not found"));
	}
	
	public void deleteJob(Long id, String employerEmail) {
		Job job = getJobById(id);
		if(!job.getPostedBy().equals(employerEmail))
			throw new RuntimeException("Unauthorized delete attempt");
		jobRepository.delete(job);
	}
}
