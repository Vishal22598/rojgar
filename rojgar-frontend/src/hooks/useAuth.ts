import { useEffect } from 'react';
import { useAuthStore } from '../context/authStore';
import { authService } from '../services/authService';

export const useAuth = () => {
  const { user, isAuthenticated, setUser, setIsAuthenticated, logout } = useAuthStore();

  useEffect(() => {
    const initAuth = async () => {
      const token = localStorage.getItem('authToken');
      if (token) {
        try {
          const currentUser = await authService.getCurrentUser();
          setUser(currentUser);
          setIsAuthenticated(true);
        } catch {
          logout();
        }
      }
    };

    initAuth();
  }, [setUser, setIsAuthenticated, logout]);

  return {
    user,
    isAuthenticated,
    setUser,
    setIsAuthenticated,
    logout,
  };
};
