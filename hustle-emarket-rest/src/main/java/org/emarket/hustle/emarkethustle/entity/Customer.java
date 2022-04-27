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
public class Customer extends User
{
	// customer fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@NonNull
	@Column(name = "firstname")
	private String firstName;

	@NonNull
	@Column(name = "lastname")
	private String lastName;

	@NonNull
	@Column(name = "username")
	private String username;

	@NonNull
	@Column(name = "password")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerdetailid")
	private CustomerDetail customerDetail;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid")
	private List<CustomerAddress> customerAddress;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerid")
	private List<Basket> basket;

	@Column(name = "role", updatable = false)
	private String role;

	@Column(name = "creationdate", updatable = false)
	private String creationDate;

	@Column(name = "modifieddate", updatable = false)
	private String modifiedDate;

	public Customer()
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
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", customerDetail=" + customerDetail + ", customerAddress="
				+ customerAddress + "]";
	}

}
