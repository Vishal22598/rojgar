import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import { authService } from '../services/authService';
import toast from 'react-hot-toast';

export function Header() {
  const navigate = useNavigate();
  const { user, isAuthenticated, logout } = useAuth();

  const handleLogout = () => {
    authService.logout();
    logout();
    toast.success('Logged out successfully');
    navigate('/login');
  };

  if (!isAuthenticated) {
    return null;
  }

  return (
    <header className="bg-white shadow-md">
      <div className="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
        <h1 className="text-2xl font-bold text-blue-600">Rojgar</h1>
        
        <nav className="flex gap-6 items-center">
          <button
            onClick={() => navigate('/dashboard')}
            className="text-gray-600 hover:text-gray-900 font-semibold"
          >
            Jobs
          </button>
          <button
            onClick={() => navigate('/notifications')}
            className="text-gray-600 hover:text-gray-900 font-semibold"
          >
            Notifications
          </button>
          <button
            onClick={() => navigate('/profile')}
            className="text-gray-600 hover:text-gray-900 font-semibold"
          >
            Profile
          </button>
          {user?.userType === 'ADMIN' && (
            <button
              onClick={() => navigate('/admin')}
              className="text-gray-600 hover:text-gray-900 font-semibold"
            >
              Admin
            </button>
          )}
        </nav>

        <div className="flex items-center gap-4">
          <span className="text-gray-700">{user?.firstName}</span>
          <button
            onClick={handleLogout}
            className="bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition"
          >
            Logout
          </button>
        </div>
      </div>
    </header>
  );
}
