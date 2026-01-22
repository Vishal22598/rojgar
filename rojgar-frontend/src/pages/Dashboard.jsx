import React, { use, useEffect, useState } from 'react'
import api from '../api/axiosConfig';

function Dashboard() {
    const [user, setUser] = useState(null);
    const [jobCount, setJobCount] = useState(0);
    const [applicationCount, setApplicationCount] = useState(0);
    const [recentJobs, setRecentJobs] = useState([]);

    useEffect(() => {
        fetchDashboardData();
    }, []);

    const fetchDashboardData = async () => {
        try {
            const userRes = await api.get('/users/me');
            const jobsRes = await api.get('/jobs');
            const appsRes = await api.get('/applications/my-applications');

            setUser(userRes.data);
            setJobCount(jobsRes.data.length);
            setApplicationCount(appsRes.data.length);
            setRecentJobs(jobsRes.data.slice(0, 5)); // Get recent 5 jobs
        } catch (error) {
            console.error('Error fetching dashboard data:', error);
        }
    };

  return (
    <div>
      <h1>Dashboard</h1>
      <p>Welcome, {user?.name}!</p>
      <p>Total Jobs: {jobCount}</p>
      <p>Applications: {applicationCount}</p>
      <h2>Recent Jobs</h2>
      <ul>
        {recentJobs.map(job => (
          <li key={job.id}>{job.title}</li>
        ))}
      </ul>
    </div>
  )
}

export default Dashboard
