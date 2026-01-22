import React, { useEffect, useState } from 'react'
import { getAllJobs } from '../services/jobService';

function Jobs() {
    const [jobs, setJobs] = useState([]);

    useEffect(() => {
        getAllJobs().then((res) => setJobs(res.data));
    }, []);

  return (
    <div>
      {jobs.map((job) => (
        <div key={job.id}>
          <h2>{job.title}</h2>
          <p>{job.description}</p>
        </div>
      ))}
    </div>
  )
}

export default Jobs