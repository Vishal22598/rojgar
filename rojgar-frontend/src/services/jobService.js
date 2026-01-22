import api from "../api/axiosConfig";

export const getAllJobs = () => api.get("/jobs");
export const applyJob = (jobId) =>
    api.post(`/applications/apply/${jobId}`);