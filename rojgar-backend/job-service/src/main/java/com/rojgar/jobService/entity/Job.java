package com.rojgar.jobService.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "jobs")
@Data
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	
	@Column(name = "company_name")
	private String companyName;
	
	private String location;
	private Double salary;
	
	@Column(name = "job_type") 
	private String jobType; //FULL_TIME, PART_TIME, CONTRACT
	
	@Column(name = "experiance_level")
	private String experianceLevel; //FRESHER, MID, SENIOR
	
	@Column(name = "posted_at")
	private LocalDateTime postedAt;
	private String postedBy; // employer email
}
