import { useEffect, useState } from 'react';
import { jobService } from '../services/jobService';
import type { Job } from '../types/job';

export default function JobListingsPage() {
  const [jobs, setJobs] = useState<Job[]>([]);
  const [loading, setLoading] = useState(true);
  const [filters, setFilters] = useState({
    search: '',
    location: '',
    jobType: '',
  });

  useEffect(() => {
    fetchJobs();
  }, [filters]);

  const fetchJobs = async () => {
    setLoading(true);
    try {
      const result = await jobService.listJobs(filters);
      setJobs(result.jobs);
    } catch (error) {
      console.error('Failed to fetch jobs:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 py-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-8">Job Listings</h1>

        {/* Filters */}
        <div className="bg-white rounded-lg shadow-md p-6 mb-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <input
              type="text"
              placeholder="Search jobs..."
              value={filters.search}
              onChange={(e) => setFilters({ ...filters, search: e.target.value })}
              className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            <input
              type="text"
              placeholder="Location..."
              value={filters.location}
              onChange={(e) => setFilters({ ...filters, location: e.target.value })}
              className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            <select
              value={filters.jobType}
              onChange={(e) => setFilters({ ...filters, jobType: e.target.value })}
              className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="">All Job Types</option>
              <option value="FULL_TIME">Full Time</option>
              <option value="PART_TIME">Part Time</option>
              <option value="CONTRACT">Contract</option>
              <option value="INTERNSHIP">Internship</option>
            </select>
          </div>
        </div>

        {/* Job List */}
        {loading ? (
          <div className="text-center py-8">Loading jobs...</div>
        ) : jobs.length === 0 ? (
          <div className="text-center py-8 text-gray-600">No jobs found</div>
        ) : (
          <div className="space-y-4">
            {jobs.map((job) => (
              <div key={job.id} className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition">
                <h2 className="text-xl font-bold text-gray-900">{job.title}</h2>
                <p className="text-gray-600">{job.company}</p>
                <p className="text-sm text-gray-500 mt-2">{job.location}</p>
                <p className="text-gray-700 mt-4">{job.description}</p>
                <div className="mt-4 flex justify-between items-center">
                  <span className="inline-block bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm">
                    {job.jobType}
                  </span>
                  <button className="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">
                    View Details
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
