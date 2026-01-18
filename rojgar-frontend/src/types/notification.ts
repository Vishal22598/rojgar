export interface Notification {
  id: string;
  userId: string;
  type: 'APPLICATION_STATUS' | 'JOB_RECOMMENDATION' | 'MESSAGE' | 'SYSTEM';
  title: string;
  message: string;
  read: boolean;
  actionUrl?: string;
  createdAt: string;
}

export interface Message {
  id: string;
  senderId: string;
  recipientId: string;
  content: string;
  attachments?: string[];
  read: boolean;
  createdAt: string;
}
