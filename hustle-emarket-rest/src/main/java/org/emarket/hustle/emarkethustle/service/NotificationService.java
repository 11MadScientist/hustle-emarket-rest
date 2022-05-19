package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Notification;

public interface NotificationService
{
	public List<Notification> getNotificationByUserAndRole(int id, String role);

	public List<Notification> getNotifications();

	public Notification getNotificationById(int id);

	public void addNotification(Notification notification);

	public void updateNotification(Notification notification);

	public void deleteNotification(Notification notification);

	int getNotifCount(int id, String role);
}
