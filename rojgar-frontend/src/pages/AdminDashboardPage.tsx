export default function AdminDashboardPage() {
  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 py-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-8">Admin Dashboard</h1>

        {/* Stats Grid */}
        <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-8">
          <div className="bg-white rounded-lg shadow-md p-6">
            <h3 className="text-gray-600 text-sm font-semibold uppercase">Total Users</h3>
            <p className="text-3xl font-bold text-gray-900 mt-2">1,234</p>
          </div>
          <div className="bg-white rounded-lg shadow-md p-6">
            <h3 className="text-gray-600 text-sm font-semibold uppercase">Active Jobs</h3>
            <p className="text-3xl font-bold text-gray-900 mt-2">456</p>
          </div>
          <div className="bg-white rounded-lg shadow-md p-6">
            <h3 className="text-gray-600 text-sm font-semibold uppercase">Total Applications</h3>
            <p className="text-3xl font-bold text-gray-900 mt-2">2,789</p>
          </div>
          <div className="bg-white rounded-lg shadow-md p-6">
            <h3 className="text-gray-600 text-sm font-semibold uppercase">Revenue</h3>
            <p className="text-3xl font-bold text-gray-900 mt-2">$12,456</p>
          </div>
        </div>

        {/* Management Sections */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div className="bg-white rounded-lg shadow-md p-6">
            <h2 className="text-xl font-bold text-gray-900 mb-4">User Management</h2>
            <button className="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition w-full">
              Manage Users
            </button>
          </div>
          <div className="bg-white rounded-lg shadow-md p-6">
            <h2 className="text-xl font-bold text-gray-900 mb-4">Job Management</h2>
            <button className="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition w-full">
              Manage Jobs
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
