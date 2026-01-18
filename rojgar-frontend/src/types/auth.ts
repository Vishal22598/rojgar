export interface User {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  phone?: string;
  avatar?: string;
  userType: 'JOB_SEEKER' | 'EMPLOYER' | 'ADMIN';
  createdAt: string;
  updatedAt: string;
}

export interface AuthResponse {
  token: string;
  refreshToken: string;
  user: User;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  userType: 'JOB_SEEKER' | 'EMPLOYER';
}
