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
@Table(name = "rider_detail")
public class RiderDetail
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

	@Column(name = "station")
	private String station;

	@Column(name = "documents")
	private String documents;

	@Column(name = "authorized")
	private boolean authorized;

	@Column(name = "prohibited")
	private boolean prohibited;

	public RiderDetail()
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

	public String getStation()
	{
		return station;
	}

	public void setStation(String station)
	{
		this.station = station;
	}

	public String getDocuments()
	{
		return documents;
	}

	public void setDocuments(String documents)
	{
		this.documents = documents;
	}

	public boolean isAuthorized()
	{
		return authorized;
	}

	public void setAuthorized(boolean authorized)
	{
		this.authorized = authorized;
	}

	public boolean isProhibited()
	{
		return prohibited;
	}

	public void setProhibited(boolean prohibited)
	{
		this.prohibited = prohibited;
	}

	@Override
	public String toString()
	{
		return "RiderDetail [id=" + id + ", age=" + age + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", documents=" + documents + ", authorized="
				+ authorized + ", prohibited=" + prohibited + "]";
	}

}
