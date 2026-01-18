import axiosInstance from './api';
import type { Notification, Message } from '../types/notification';

export const notificationService = {
  getNotifications: async (unreadOnly?: boolean): Promise<Notification[]> => {
    const { data } = await axiosInstance.get('/notifications', {
      params: { unreadOnly },
    });
    return data;
  },

  markAsRead: async (id: string): Promise<void> => {
    await axiosInstance.put(`/notifications/${id}/read`);
  },

  markAllAsRead: async (): Promise<void> => {
    await axiosInstance.put('/notifications/read-all');
  },

  deleteNotification: async (id: string): Promise<void> => {
    await axiosInstance.delete(`/notifications/${id}`);
  },

  getMessages: async (userId?: string): Promise<Message[]> => {
    const { data } = await axiosInstance.get('/messages', { params: { userId } });
    return data;
  },

  sendMessage: async (recipientId: string, content: string): Promise<Message> => {
    const { data } = await axiosInstance.post('/messages', { recipientId, content });
    return data;
  },
};
