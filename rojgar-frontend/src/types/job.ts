export interface Job {
  id: string;
  title: string;
  description: string;
  company: string;
  location: string;
  salary?: {
    min: number;
    max: number;
    currency: string;
  };
  jobType: 'FULL_TIME' | 'PART_TIME' | 'CONTRACT' | 'INTERNSHIP';
  experience: string;
  skills: string[];
  postedBy: string;
  postedAt: string;
  deadline: string;
  applicantCount: number;
  status: 'ACTIVE' | 'CLOSED' | 'DRAFT';
}

export interface JobApplication {
  id: string;
  jobId: string;
  applicantId: string;
  appliedAt: string;
  status: 'APPLIED' | 'REVIEWED' | 'SHORTLISTED' | 'REJECTED' | 'ACCEPTED';
  resumeUrl?: string;
  notes?: string;
}

export interface JobFilter {
  search?: string;
  location?: string;
  jobType?: string;
  experience?: string;
  salaryMin?: number;
  salaryMax?: number;
  page?: number;
  limit?: number;
}
