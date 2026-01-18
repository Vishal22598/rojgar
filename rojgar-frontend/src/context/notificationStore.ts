import { create } from 'zustand';
import type { Notification } from '../types/notification';

interface NotificationStore {
  notifications: Notification[];
  unreadCount: number;
  addNotification: (notification: Notification) => void;
  removeNotification: (id: string) => void;
  setNotifications: (notifications: Notification[]) => void;
  updateUnreadCount: (count: number) => void;
}

export const useNotificationStore = create<NotificationStore>((set) => ({
  notifications: [],
  unreadCount: 0,
  addNotification: (notification) =>
    set((state) => ({
      notifications: [notification, ...state.notifications],
      unreadCount: state.unreadCount + (notification.read ? 0 : 1),
    })),
  removeNotification: (id) =>
    set((state) => ({
      notifications: state.notifications.filter((n) => n.id !== id),
    })),
  setNotifications: (notifications) => set({ notifications }),
  updateUnreadCount: (count) => set({ unreadCount: count }),
}));
