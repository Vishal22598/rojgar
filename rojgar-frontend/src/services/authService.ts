import axiosInstance from './api';
import type { User, AuthResponse, LoginRequest, RegisterRequest } from '../types/auth';

export const authService = {
  login: async (credentials: LoginRequest): Promise<AuthResponse> => {
    const { data } = await axiosInstance.post('/auth/login', credentials);
    localStorage.setItem('authToken', data.token);
    localStorage.setItem('refreshToken', data.refreshToken);
    return data;
  },

  register: async (userData: RegisterRequest): Promise<AuthResponse> => {
    const { data } = await axiosInstance.post('/auth/register', userData);
    localStorage.setItem('authToken', data.token);
    localStorage.setItem('refreshToken', data.refreshToken);
    return data;
  },

  logout: (): void => {
    localStorage.removeItem('authToken');
    localStorage.removeItem('refreshToken');
  },

  getCurrentUser: async (): Promise<User> => {
    const { data } = await axiosInstance.get('/auth/me');
    return data;
  },

  updateProfile: async (userId: string, updates: Partial<User>): Promise<User> => {
    const { data } = await axiosInstance.put(`/users/${userId}`, updates);
    return data;
  },
};
