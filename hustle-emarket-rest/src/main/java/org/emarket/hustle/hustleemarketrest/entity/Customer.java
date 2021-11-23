package org.emarket.hustle.hustleemarketrest.entity;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_detail_id")
	private CustomerDetail customerDetail;

	@JsonManagedReference
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerAddress> customerAddress;

	public Customer()
	{
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

	public String getPassword()
	{
		return password;
	}

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

	public List<CustomerAddress> getCustomerAddress()
	{
		return customerAddress;
	}

	public void setCustomerAddress(List<CustomerAddress> customerAddress)
	{

		this.customerAddress = customerAddress;
	}

	@Override
	public String toString()
	{
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", customerDetail=" + customerDetail + ", customerAddress="
				+ customerAddress + "]";
	}

}
