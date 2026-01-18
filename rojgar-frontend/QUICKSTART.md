# Rojgar Frontend - Quick Start Guide

## Prerequisites
- Node.js 16+ and npm 8+
- Backend API running at http://localhost:8080

## Setup Steps

### 1. Install Dependencies
```bash
npm install
```

### 2. Configure Environment
```bash
# Copy example environment file
cp .env.example .env.local

# Edit .env.local and set API URL (default: http://localhost:8080/api)
```

### 3. Start Development Server
```bash
npm run dev
```
- Opens at http://localhost:5173
- Automatically reloads on file changes
- API requests proxy to backend via `/api` path

### 4. Verify Everything Works
- Navigate to http://localhost:5173/login
- Should see login page

## Available Scripts

- `npm run dev` - Start dev server with HMR
- `npm run build` - Production build to `dist/`
- `npm run preview` - Preview production build
- `npm run type-check` - Check TypeScript errors
- `npm run lint` - Run ESLint

## Folder Structure Reference

- **src/pages/** - Routed page components (LoginPage, JobListingsPage, etc.)
- **src/services/** - API integration layer (authService, jobService, etc.)
- **src/context/** - Zustand state stores (authStore, notificationStore)
- **src/hooks/** - Custom React hooks (useAuth)
- **src/types/** - TypeScript type definitions
- **src/utils/** - Helper functions (formatters, utilities)
- **src/components/** - Reusable UI components

## Authentication Flow

1. Login at `/login` with email/password
2. Receive JWT token stored in localStorage
3. Token automatically sent with all API requests
4. Protected routes redirect to login if not authenticated
5. Token refreshed on 401 response

## Making Your First API Call

```typescript
import { jobService } from './services/jobService';
import toast from 'react-hot-toast';

try {
  const jobs = await jobService.listJobs({ search: 'React' });
  console.log(jobs);
} catch (error) {
  toast.error('Failed to fetch jobs');
}
```

## Common Issues & Solutions

### Port 5173 Already in Use
```bash
npm run dev -- --port 3000
```

### API Connection Failed
- Check backend is running at configured URL
- Verify `VITE_API_URL` in `.env.local`
- Check browser console for CORS errors

### TypeScript Errors
```bash
npm run type-check
```

## Next Steps

1. Explore the existing page components in `src/pages/`
2. Review the API service layer in `src/services/`
3. Check [.github/copilot-instructions.md](.github/copilot-instructions.md) for detailed architecture
4. Review [README.md](README.md) for full project documentation

## Need Help?

- Check the comprehensive `.github/copilot-instructions.md` for architecture details
- Review existing service implementations for API patterns
- Check React Router docs for routing questions
- Review Tailwind CSS docs for styling

---

**Dev Server**: http://localhost:5173  
**Backend API**: http://localhost:8080/api (configurable)
