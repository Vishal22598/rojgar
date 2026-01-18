# Rojgar Frontend

A modern job marketplace platform built with React, TypeScript, Vite, and Tailwind CSS. This frontend application connects to a microservices backend to deliver a complete job listing, application, and user management experience.

## Features

- 🔐 **Authentication**: User login, registration, and session management
- 💼 **Job Listings**: Browse, filter, and search job opportunities
- 📋 **Applications**: Apply for jobs and track application status
- 👤 **User Profiles**: Manage personal and professional information
- 🔔 **Notifications**: Real-time notifications for job recommendations and applications
- 👨‍💼 **Admin Dashboard**: Comprehensive admin tools for user and job management
- 📱 **Responsive Design**: Mobile-first UI built with Tailwind CSS
- 🔒 **Type Safety**: Full TypeScript support throughout the application

## Tech Stack

- **Framework**: React 19 with TypeScript
- **Build Tool**: Vite (lightning-fast builds)
- **Styling**: Tailwind CSS
- **Routing**: React Router v6
- **State Management**: Zustand
- **HTTP Client**: Axios
- **Notifications**: react-hot-toast
- **Package Manager**: npm

## Getting Started

### Prerequisites

- Node.js 16+ and npm 8+
- Backend services running at http://localhost:8080 (configurable)

### Installation

```bash
# Clone the repository
git clone <repo-url>
cd rojgar-frontend

# Install dependencies
npm install

# Create environment file
cp .env.example .env.local
```

### Configuration

Edit `.env.local` to configure the API endpoint:

```env
VITE_API_URL=http://localhost:8080/api
```

### Development

```bash
# Start dev server (http://localhost:5173)
npm run dev

# Run type checking
npm run type-check

# Run ESLint
npm run lint
```

### Build

```bash
# Build for production
npm run build

# Preview production build
npm run preview
```

## Project Structure

```
src/
├── components/          # Reusable UI components
├── pages/              # Page-level components (routed views)
│   ├── LoginPage.tsx
│   ├── RegisterPage.tsx
│   ├── JobListingsPage.tsx
│   ├── ProfilePage.tsx
│   ├── NotificationsPage.tsx
│   └── AdminDashboardPage.tsx
├── services/           # API integration layer
│   ├── api.ts         # Axios instance with interceptors
│   ├── authService.ts # Authentication APIs
│   ├── jobService.ts  # Job management APIs
│   └── notificationService.ts
├── hooks/             # Custom React hooks
│   └── useAuth.ts     # Authentication hook
├── context/           # Zustand stores
│   ├── authStore.ts   # Global auth state
│   └── notificationStore.ts
├── types/             # TypeScript interfaces
│   ├── auth.ts
│   ├── job.ts
│   └── notification.ts
├── utils/             # Helper functions
│   └── formatters.ts
├── App.tsx            # Main app router
└── main.tsx           # Entry point
```

## API Integration

The frontend connects to these backend services:

- **Auth Service** (`/api/auth/`): Login, register, user profile
- **Job Service** (`/api/jobs/`): Job CRUD, applications, filtering
- **User Service** (`/api/users/`): User profiles, settings
- **Notification Service** (`/api/notifications/`): Notifications, messages

### Making API Calls

```typescript
// Example: Fetch jobs
import { jobService } from './services/jobService';

const jobs = await jobService.listJobs({ search: 'React', location: 'New York' });
```

## Authentication

The application uses JWT-based authentication:

1. Login → receives `token` and `refreshToken`
2. Token stored in `localStorage` with key `authToken`
3. All requests include `Authorization: Bearer <token>` header
4. 401 responses automatically redirect to login
5. Protected routes use `<ProtectedRoute>` wrapper

## State Management

Uses **Zustand** for global state:

```typescript
// Access auth state
const { user, isAuthenticated } = useAuthStore();

// Update state
setUser(newUser);
setIsAuthenticated(true);
```

## Styling

- **Framework**: Tailwind CSS for utility-first styling
- **Colors**: Blue primary (#3b82f6), green secondary (#10b981)
- **Responsive**: Mobile-first breakpoints (md:, lg:, xl:)
- **No CSS modules or inline styles** - Tailwind only

## Error Handling

API errors are handled with `react-hot-toast` notifications:

```typescript
try {
  await jobService.applyForJob(jobId);
  toast.success('Application submitted!');
} catch (error) {
  toast.error(error.response?.data?.message || 'Failed to apply');
}
```

## Development Guidelines

### Adding a New Page

1. Create component in `src/pages/NewPage.tsx`
2. Add route in `App.tsx`:
   ```tsx
   <Route path="/new" element={<ProtectedRoute><NewPage /></ProtectedRoute>} />
   ```
3. Add navigation link

### Adding API Integration

1. Add method to appropriate service file
2. Use typed responses and error handling
3. Display results or errors in component

### Creating New Types

All API types should be defined in `src/types/` before use.

## Performance Tips

- **Lazy load routes** for large applications
- **Memoize expensive components** with React.memo
- **Use useCallback** for event handlers passed to children
- **Optimize images** for web delivery
- **Minimize bundle** with tree-shaking and code splitting

## Deployment

```bash
# Build production bundle
npm run build

# Output in dist/ directory
# Deploy to your hosting platform (Vercel, Netlify, etc.)
```

## Contributing

1. Follow the project's code structure and naming conventions
2. Use TypeScript for all new code
3. Run `npm run lint` before committing
4. Ensure all components are typed properly

## Support

For issues, questions, or contributions, please refer to the project's issue tracker or documentation.

---

**Backend API**: http://localhost:8080/api  
**Dev Server**: http://localhost:5173  
**Last Updated**: January 2026
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Other configs...

      // Remove tseslint.configs.recommended and replace with this
      tseslint.configs.recommendedTypeChecked,
      // Alternatively, use this for stricter rules
      tseslint.configs.strictTypeChecked,
      // Optionally, add this for stylistic rules
      tseslint.configs.stylisticTypeChecked,

      // Other configs...
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
      // other options...
    },
  },
])
```

You can also install [eslint-plugin-react-x](https://github.com/Rel1cx/eslint-react/tree/main/packages/plugins/eslint-plugin-react-x) and [eslint-plugin-react-dom](https://github.com/Rel1cx/eslint-react/tree/main/packages/plugins/eslint-plugin-react-dom) for React-specific lint rules:

```js
// eslint.config.js
import reactX from 'eslint-plugin-react-x'
import reactDom from 'eslint-plugin-react-dom'

export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{ts,tsx}'],
    extends: [
      // Other configs...
      // Enable lint rules for React
      reactX.configs['recommended-typescript'],
      // Enable lint rules for React DOM
      reactDom.configs.recommended,
    ],
    languageOptions: {
      parserOptions: {
        project: ['./tsconfig.node.json', './tsconfig.app.json'],
        tsconfigRootDir: import.meta.dirname,
      },
      // other options...
    },
  },
])
```
