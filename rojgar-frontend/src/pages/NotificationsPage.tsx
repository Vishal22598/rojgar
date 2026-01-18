import { useNotificationStore } from '../context/notificationStore';
import { useEffect } from 'react';
import { notificationService } from '../services/notificationService';

export default function NotificationsPage() {
  const { notifications, setNotifications } = useNotificationStore();

  useEffect(() => {
    fetchNotifications();
  }, []);

  const fetchNotifications = async () => {
    try {
      const data = await notificationService.getNotifications();
      setNotifications(data);
    } catch (error) {
      console.error('Failed to fetch notifications:', error);
    }
  };

  const handleMarkAsRead = async (id: string) => {
    try {
      await notificationService.markAsRead(id);
      await fetchNotifications();
    } catch (error) {
      console.error('Failed to mark notification as read:', error);
    }
  };

  const handleDelete = async (id: string) => {
    try {
      await notificationService.deleteNotification(id);
      await fetchNotifications();
    } catch (error) {
      console.error('Failed to delete notification:', error);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-4xl mx-auto px-4 py-8">
        <h1 className="text-3xl font-bold text-gray-900 mb-8">Notifications</h1>

        {notifications.length === 0 ? (
          <div className="bg-white rounded-lg shadow-md p-8 text-center text-gray-600">
            No notifications yet
          </div>
        ) : (
          <div className="space-y-4">
            {notifications.map((notification) => (
              <div
                key={notification.id}
                className={`bg-white rounded-lg shadow-md p-6 ${
                  !notification.read ? 'border-l-4 border-blue-500' : ''
                }`}
              >
                <div className="flex justify-between items-start">
                  <div className="flex-1">
                    <h3 className="text-lg font-semibold text-gray-900">{notification.title}</h3>
                    <p className="text-gray-600 mt-2">{notification.message}</p>
                    <p className="text-sm text-gray-500 mt-2">
                      {new Date(notification.createdAt).toLocaleDateString()}
                    </p>
                  </div>
                  <div className="flex gap-2">
                    {!notification.read && (
                      <button
                        onClick={() => handleMarkAsRead(notification.id)}
                        className="text-blue-600 hover:text-blue-700 font-semibold text-sm"
                      >
                        Mark as Read
                      </button>
                    )}
                    <button
                      onClick={() => handleDelete(notification.id)}
                      className="text-red-600 hover:text-red-700 font-semibold text-sm"
                    >
                      Delete
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
