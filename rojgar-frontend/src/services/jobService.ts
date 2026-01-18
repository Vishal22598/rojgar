import axiosInstance from './api';
import type { Job, JobApplication, JobFilter } from '../types/job';

export const jobService = {
  listJobs: async (filters?: JobFilter): Promise<{ jobs: Job[]; total: number }> => {
    const { data } = await axiosInstance.get('/jobs', { params: filters });
    return data;
  },

  getJob: async (id: string): Promise<Job> => {
    const { data } = await axiosInstance.get(`/jobs/${id}`);
    return data;
  },

  createJob: async (job: Partial<Job>): Promise<Job> => {
    const { data } = await axiosInstance.post('/jobs', job);
    return data;
  },

  updateJob: async (id: string, updates: Partial<Job>): Promise<Job> => {
    const { data } = await axiosInstance.put(`/jobs/${id}`, updates);
    return data;
  },

  deleteJob: async (id: string): Promise<void> => {
    await axiosInstance.delete(`/jobs/${id}`);
  },

  applyForJob: async (jobId: string, resumeUrl?: string): Promise<JobApplication> => {
    const { data } = await axiosInstance.post(`/jobs/${jobId}/apply`, { resumeUrl });
    return data;
  },

  getApplications: async (jobId?: string): Promise<JobApplication[]> => {
    const { data } = await axiosInstance.get('/applications', { params: { jobId } });
    return data;
  },

  updateApplicationStatus: async (id: string, status: string): Promise<JobApplication> => {
    const { data } = await axiosInstance.put(`/applications/${id}`, { status });
    return data;
  },
};
