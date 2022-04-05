package org.emarket.hustle.emarkethustle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "notification")
public class Notification
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "user_id", updatable = false)
	private int userId;

	@Column(name = "link")
	private String link;

	@Column(name = "notif_type")
	private String notifType;

	@Column(name = "isread")
	private boolean isRead;

	@Column(name = "role", updatable = false)
	private String role;

	@Column(name = "message", updatable = false)
	private String message;

	@Column(name = "creation_date", updatable = false)
	private String creationDate;

	@Column(name = "read_date")
	private String readDate;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getNotifType()
	{
		return notifType;
	}

	public void setNotifType(String notifType)
	{
		this.notifType = notifType;
	}

	public boolean isRead()
	{
		return isRead;
	}

	public void setRead(boolean isRead)
	{
		this.isRead = isRead;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(String creationDate)
	{
		this.creationDate = creationDate;
	}

	public String getReadDate()
	{
		return readDate;
	}

	public void setReadDate(String readDate)
	{
		this.readDate = readDate;
	}

	@Override
	public String toString()
	{
		return "Notification [id=" + id + ", userId=" + userId + ", link=" + link + ", notifType=" + notifType
				+ ", isRead=" + isRead + ", role=" + role + ", message=" + message + ", creationDate=" + creationDate
				+ ", readDate=" + readDate + "]";
	}

}
