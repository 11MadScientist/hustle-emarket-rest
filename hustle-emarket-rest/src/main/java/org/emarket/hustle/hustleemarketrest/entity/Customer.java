package org.emarket.hustle.hustleemarketrest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "customer")
public class Customer
{
	// customer fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_detail_id")
	private CustomerDetail customerDetail;

	@JsonManagedReference
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerAddress> customerAddress;

	@Column(name = "creation_date")
	private long creationDate;

	@Column(name = "modified_date")
	private long modifiedDate;

	public Customer()
	{
		modifiedDate = System.currentTimeMillis();
	}

	public Customer(String firstName, String lastName, String username, String password, CustomerDetail customerDetail)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.customerDetail = customerDetail;
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

	@JsonInclude(Include.NON_EMPTY)
	public List<CustomerAddress> getCustomerAddress()
	{
		return customerAddress;
	}

	public void setCustomerAddress(List<CustomerAddress> customerAddress)
	{

		this.customerAddress = customerAddress;
	}

	public long getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(long creationDate)
	{
		this.creationDate = creationDate;
	}

	public long getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(long modifiedDate)
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
