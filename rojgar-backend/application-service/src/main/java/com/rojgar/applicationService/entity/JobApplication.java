package com.rojgar.applicationService.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
@Entity
@Table(
		name = "job_application",
		uniqueConstraints = @UniqueConstraint(
				columnNames = {"jobId", "applicationEmail"}
		)
)
@Data
public class JobApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long jobId;
	private String applicationEmail;
	
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;
	
	private LocalDateTime appliedAt;
}
