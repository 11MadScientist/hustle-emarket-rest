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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.core.lang.NonNull;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "customer")
public class Customer
{
	// customer fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@NonNull
	@Column(name = "first_name")
	private String firstName;

	@NonNull
	@Column(name = "last_name")
	private String lastName;

	@NonNull
	@Column(name = "username")
	private String username;

	@NonNull
	@Column(name = "password")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_detail_id")
	private CustomerDetail customerDetail;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<CustomerAddress> customerAddress;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private List<Basket> basket;

	@Column(name = "role")
	private String role;

	@Column(name = "creation_date", updatable = false)
	private String creationDate;

	@Column(name = "modified_date", updatable = false)
	private String modifiedDate;

	public Customer()
	{

	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

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

	public CustomerDetail getCustomerDetail()
	{
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail)
	{
		this.customerDetail = customerDetail;
	}

	@JsonIgnore
	public List<CustomerAddress> getCustomerAddress()
	{
		return customerAddress;
	}

	@JsonProperty
	public void setCustomerAddress(List<CustomerAddress> customerAddress)
	{
		this.customerAddress = customerAddress;
	}

	@JsonIgnore
	public List<Basket> getBasket()
	{
		return basket;
	}

	public void setBasket(List<Basket> basket)
	{
		this.basket = basket;
	}

	public String getRole()
	{
		return role;
	}

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
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", customerDetail=" + customerDetail + ", customerAddress="
				+ customerAddress + "]";
	}

}
