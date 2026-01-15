package com.rojgar.applicationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rojgar.applicationService.entity.JobApplication;
import java.util.List;


public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{
	boolean existByJobIdAndApplicationEmail(Long jobId, String applicationEmail);
	List<JobApplication> findByApplicationEmail(String applicationEmail);
	List<JobApplication> findByJobId(Long jobId);
}
