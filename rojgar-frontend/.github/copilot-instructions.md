# Rojgar Frontend - AI Coding Agent Instructions

## Project Overview
Rojgar is a job marketplace platform built with **React + TypeScript + Vite + Tailwind CSS**. The frontend is a SPA that communicates with a microservices backend through REST APIs. It features authentication, job listings, user profiles, applications management, notifications, and an admin dashboard.

## Architecture & Key Patterns

### Folder Structure
```
src/
├── components/      # Reusable UI components
├── pages/          # Page-level components (routed views)
├── services/       # API integration layer (authService, jobService, notificationService)
├── hooks/          # Custom React hooks (useAuth for authentication state)
├── context/        # Zustand stores for global state (authStore, notificationStore)
├── types/          # TypeScript interfaces (auth, job, notification)
├── utils/          # Helper functions (formatters, utilities)
└── App.tsx         # Main router setup
```

### State Management Pattern
- **Zustand** for global state: `useAuthStore`, `useNotificationStore`
- **Context pattern**: Stores accessed via hooks from any component
- **Local state**: Use `useState` for component-level state
- **Example**: `const { user, isAuthenticated } = useAuthStore()` in components

### API Layer Pattern
- **Axios instance** in `services/api.ts` with:
  - Base URL from `VITE_API_URL` environment variable
  - Request interceptor: Adds `Authorization: Bearer <token>` header
  - Response interceptor: Handles 401 errors (redirects to login)
- **Service modules** (`authService`, `jobService`, `notificationService`):
  - Export object with methods calling `axiosInstance`
  - Handle API errors internally or let components catch
  - Return typed responses using TypeScript interfaces
  - Example pattern: `await jobService.listJobs(filters)`

### Authentication Flow
1. User logs in via `LoginPage` → calls `authService.login()`
2. Response contains `token`, `refreshToken`, and `user` object
3. Token stored in `localStorage` with key `authToken`
4. `useAuth()` hook initializes auth state on app load
5. Protected routes wrap components in `<ProtectedRoute>` component
6. 401 errors automatically redirect to `/login`

### Type Safety
- All API responses and requests are fully typed
- Key types: `User`, `Job`, `JobApplication`, `Notification` in `src/types/`
- Keep types synchronized with backend contract
- Use `Partial<Type>` for optional updates

## Development Workflows

### Setup & Running
```bash
npm install                  # Install dependencies
npm run dev                 # Start Vite dev server (http://localhost:5173)
npm run build              # Production build
npm run type-check         # Check TypeScript errors
npm run lint               # ESLint validation
```

### Environment Configuration
- Copy `.env.example` to `.env.local`
- Configure `VITE_API_URL` to point to backend (default: http://localhost:8080/api)
- Vite dev server proxies `/api` requests to backend (see `vite.config.ts`)

### API Integration
1. Create service method in appropriate file (`services/jobService.ts`, etc.)
2. Use `axiosInstance` for HTTP calls
3. Return typed response
4. Catch errors in component or service level
5. Example:
   ```typescript
   // In jobService.ts
   getJob: async (id: string): Promise<Job> => {
     const { data } = await axiosInstance.get(`/jobs/${id}`);
     return data;
   }
   ```

### Adding New Features
1. **Type first**: Add interfaces to `src/types/`
2. **Service layer**: Add API methods to relevant `services/` file
3. **State management**: Use `useAuthStore`/`useNotificationStore` or `useState`
4. **Components**: Build in `src/components/` (reusable) or `src/pages/` (routed)
5. **Routing**: Add `<Route>` in `App.tsx` and protect if needed

## Key Files & Their Responsibilities

| File | Purpose |
|------|---------|
| `src/App.tsx` | Main router; defines all routes and protected route logic |
| `src/services/api.ts` | Axios instance with interceptors for auth token + 401 handling |
| `src/services/authService.ts` | Login, register, logout, user fetch operations |
| `src/services/jobService.ts` | Job CRUD, applications, filtering |
| `src/services/notificationService.ts` | Notifications, messages |
| `src/context/authStore.ts` | Global auth state (user, isAuthenticated) |
| `src/context/notificationStore.ts` | Global notification state |
| `src/hooks/useAuth.ts` | Custom hook for auth initialization and state access |
| `src/types/*.ts` | TypeScript contracts matching backend APIs |

## Important Conventions

### Naming
- **Page components**: PascalCase + `Page` suffix (e.g., `LoginPage.tsx`, `JobListingsPage.tsx`)
- **Service files**: camelCase + `Service` suffix (e.g., `authService.ts`)
- **Stores**: camelCase + `Store` suffix (e.g., `authStore.ts`)
- **Hooks**: camelCase + `use` prefix (e.g., `useAuth.ts`)

### Error Handling
- API errors: Catch at component level, display with `react-hot-toast`
- Toast notifications: Import `toast` from `react-hot-toast`
- Example: `catch (error) { toast.error(error.response?.data?.message) }`

### Styling
- **Tailwind CSS only**: No CSS modules or inline styles
- **Color scheme**: Blue primary (#3b82f6), green secondary (#10b981)
- **Responsive**: Mobile-first using `md:`, `lg:` breakpoints
- **Button pattern**: `bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition`

### Component Structure
- Functional components only (hooks-based)
- Props should be typed with TypeScript interfaces
- Use `export default` for page components
- Keep components focused on single responsibility

## Microservices Integration Points

The frontend connects to these backend services:
- **Auth Service** (`/api/auth/`): Login, register, profile
- **Job Service** (`/api/jobs/`): Job CRUD, applications, filtering
- **User Service** (`/api/users/`): User profiles, settings
- **Notification Service** (`/api/notifications/`): Notifications, messages

All requests go through `http://localhost:8080/api` by default. Update `VITE_API_URL` to change.

## Common Tasks

### Creating a New Protected Page
1. Create component in `src/pages/NewPage.tsx`
2. Add route in `App.tsx`: `<Route path="/new" element={<ProtectedRoute><NewPage /></ProtectedRoute>} />`
3. Add navigation link pointing to `/new`

### Adding API Call
1. Add method to appropriate service file
2. Use `axiosInstance.get/post/put/delete`
3. Catch error: `catch (error: any) { toast.error(...) }`
4. Return typed response

### Displaying Notifications
1. Access store: `const notifications = useNotificationStore(state => state.notifications)`
2. Call service: `await notificationService.markAsRead(id)`
3. Refresh from API: `const data = await notificationService.getNotifications()`

---

**Last Updated**: January 2026  
**Backend Base URL**: http://localhost:8080/api  
**Dev Server**: http://localhost:5173
