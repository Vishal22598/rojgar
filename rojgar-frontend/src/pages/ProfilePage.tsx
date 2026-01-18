import { useAuth } from '../hooks/useAuth';

export default function ProfilePage() {
  const { user } = useAuth();

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-4xl mx-auto px-4 py-8">
        <div className="bg-white rounded-lg shadow-md p-8">
          <div className="flex items-start gap-6">
            {user.avatar && (
              <img
                src={user.avatar}
                alt={user.firstName}
                className="w-32 h-32 rounded-full object-cover"
              />
            )}
            <div className="flex-1">
              <h1 className="text-3xl font-bold text-gray-900">
                {user.firstName} {user.lastName}
              </h1>
              <p className="text-gray-600">{user.email}</p>
              {user.phone && <p className="text-gray-600">{user.phone}</p>}
              <div className="mt-4">
                <span className="inline-block bg-blue-100 text-blue-800 px-4 py-2 rounded-full text-sm font-semibold">
                  {user.userType === 'JOB_SEEKER' ? 'Job Seeker' : 'Employer'}
                </span>
              </div>
            </div>
            <button className="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition">
              Edit Profile
            </button>
          </div>

          <div className="mt-8 border-t pt-8">
            <h2 className="text-2xl font-bold text-gray-900 mb-4">About</h2>
            <p className="text-gray-700">No bio added yet</p>
          </div>
        </div>
      </div>
    </div>
  );
}
