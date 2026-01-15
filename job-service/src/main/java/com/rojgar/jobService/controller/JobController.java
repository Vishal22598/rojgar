package com.rojgar.jobService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rojgar.jobService.entity.Job;
import com.rojgar.jobService.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

	private final JobService jobService;

	@PostMapping
	public ResponseEntity<?> createJob(@RequestBody Job job,
			@RequestHeader(value = "X-User-Email", required = false) String email) {
		System.out.println("inside jobController: " + job + " " + email);
		return ResponseEntity.ok(jobService.createJob(job, email));
	}

	@GetMapping
	public ResponseEntity<List<Job>> getAllJobs() {
		return ResponseEntity.ok(jobService.getAllJobs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getJob(@PathVariable Long id) {
		return ResponseEntity.ok(jobService.getJobById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteJob(@PathVariable Long id, @RequestHeader("X-User-Email") String email) {
		jobService.deleteJob(id, email);
		return ResponseEntity.noContent().build();
	}
}
