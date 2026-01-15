package com.rojgar.jobService.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rojgar.jobService.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	List<Job> findByLocationIgnoreCase(String location);
	List<Job> findByTitleContainingIgnoreCase(String keyword);
}
