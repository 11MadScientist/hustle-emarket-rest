package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Integer>
{

	@Query("SELECT n from Notification n WHERE n.userId =:userId AND n.role =:role")
	public List<Notification> findByUserAndRole(
			@Param("userId") int userId,
			@Param("role") String role);

}
