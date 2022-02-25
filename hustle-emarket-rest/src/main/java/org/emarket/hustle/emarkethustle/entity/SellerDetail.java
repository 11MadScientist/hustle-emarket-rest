package org.emarket.hustle.emarkethustle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.NonNull;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "seller_detail")
public class SellerDetail
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "age")
	private Integer age;

	@Column(name = "gender")
	private String gender;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@NonNull
	@Column(name = "email")
	private String email;

	@NonNull
	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "status")
	private boolean status;

	@Column(name = "authorized")
	private boolean authorized;

	public SellerDetail()
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

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public boolean isStatus()
	{
		return status;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

	public boolean isAuthorized()
	{
		return authorized;
	}

	public void setAuthorized(boolean authorized)
	{
		this.authorized = authorized;
	}

	@Override
	public String toString()
	{
		return "SellerDetail [id=" + id + ", age=" + age + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", status=" + status + ", authorized="
				+ authorized + "]";
	}

}
