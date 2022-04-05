package org.emarket.hustle.emarkethustle.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
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
	@JoinColumn(name = "rider_detail_id", updatable = false)
	private RiderDetail riderDetail;

	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "rider")
	private List<Transaction> transactions;

	@Column(name = "creation_date", updatable = false)
	private String creationDate;

	@Column(name = "modified_date")
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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@JsonIgnore
	public String getPassword()
	{
		return password;
	}

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

	public List<Transaction> getTransactions()
	{
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions)
	{
		this.transactions = transactions;
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
