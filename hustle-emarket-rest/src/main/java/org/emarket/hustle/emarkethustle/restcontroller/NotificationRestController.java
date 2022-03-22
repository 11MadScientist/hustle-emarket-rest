package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Notification;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.NotificationService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class NotificationRestController
{
	@Autowired
	private NotificationService notificationService;

	Logger log = Logger.getLogger(NotificationRestController.class.getName());

	/*
	 * #######################################
	 * ######### GET NOTIFICATION ############
	 * #######################################
	 */

	@GetMapping("/notifications")
	public List<Notification> getNotificationByUserAndRole(
			@RequestParam int id,
			@RequestParam String role)
	{
		return notificationService.getNotificationByUserAndRole(id, role);
	}

	/*
	 * #######################################
	 * ######### ADD NOTIFICATION ############
	 * #######################################
	 */

	@PostMapping("/notifications")
	public Notification addNotification(@RequestBody Notification notification)
	{
		notificationService.addNotification(notification);
		return notification;
	}

	/*
	 * #######################################
	 * ######### ADD NOTIFICATION ############
	 * #######################################
	 */

	@PutMapping("/notifications")
	public Notification updateNotification(@RequestBody Notification notification)
	{
		notificationService.updateNotification(notification);
		return notification;
	}

	@DeleteMapping("/notifications")
	public ProcessConfirmation deleteNotification(@RequestBody Notification notification)
	{
		notificationService.deleteNotification(notification);

		return new ProcessConfirmation("SUCCESS", "NOTIFICATION",
				"THE ITEM WITH ID:" + notification.getId() + " WAS DELETED.");

	}
}
