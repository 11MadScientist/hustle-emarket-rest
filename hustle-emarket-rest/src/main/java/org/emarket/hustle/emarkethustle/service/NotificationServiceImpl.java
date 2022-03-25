package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.NotificationRepository;
import org.emarket.hustle.emarkethustle.entity.Notification;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService
{

	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public List<Notification> getNotificationByUserAndRole(int id, String role)
	{
		try
		{
			return notificationRepository.findByUserAndRole(id, role);
		}
		catch (Exception e)
		{
			throw new NotFoundException("NOTIFICATIONS");
		}
	}

	@Override
	public List<Notification> getNotifications()
	{
		try
		{
			return notificationRepository.findAll();
		}
		catch (Exception e)
		{
			throw new NotFoundException("NOTIFICATIONS");
		}
	}

	@Override
	public Notification getNotificationById(int id)
	{
		Optional<Notification> notification = notificationRepository.findById(id);

		if(notification.isEmpty())
		{
			return null;
		}

		return notification.get();
	}

	@Override
	public void addNotification(Notification notification)
	{
		notification.setId(0);
		notificationRepository.save(notification);

	}

	@Override
	public void updateNotification(Notification notification)
	{
		notificationRepository.save(notification);

	}

	@Override
	public void deleteNotification(Notification notification)
	{
		notificationRepository.delete(notification);

	}

}