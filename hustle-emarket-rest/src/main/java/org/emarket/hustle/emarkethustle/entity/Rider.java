package org.emarket.hustle.emarkethustle.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "rider")
@JsonIdentityInfo(
		scope = Rider.class,
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Rider extends User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;

	@Column(name = "role")
	private String role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "riderdetailid", updatable = false)
	private RiderDetail riderDetail;


	@Column(name = "creationdate", updatable = false)
	private String creationDate;

	@Column(name = "modifieddate")
	private String modifiedDate;

	public Rider()
	{

	}

	@Override
	public int getId()
	{
		return id;
	}

	@Override
	public void setId(int id)
	{
		this.id = id;
	}

	@Override
	public String getFirstName()
	{
		return firstName;
	}

	@Override
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	@Override
	public String getLastName()
	{
		return lastName;
	}

	@Override
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public void setUsername(String username)
	{
		this.username = username;
	}

	@Override
	@JsonIgnore
	public String getPassword()
	{
		return password;
	}

	@Override
	@JsonProperty
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public RiderDetail getRiderDetail()
	{
		return riderDetail;
	}

	public void setRiderDetail(RiderDetail riderDetail)
	{
		this.riderDetail = riderDetail;
	}

	@Override
	public String getRole()
	{
		return role;
	}

	@Override
	public void setRole(String role)
	{
		this.role = role;
	}

	public String getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(String creationDate)
	{
		this.creationDate = creationDate;
	}

	public String getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString()
	{
		return "Rider [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", riderDetail=" + riderDetail + ", creationDate=" + creationDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
