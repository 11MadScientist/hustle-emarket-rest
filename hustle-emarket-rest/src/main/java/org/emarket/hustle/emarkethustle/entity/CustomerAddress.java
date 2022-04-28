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
@Table(name = "customeraddress")
public class CustomerAddress
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "province")
	private String province;

	@Column(name = "city")
	private String city;

	@Column(name = "barangay")
	private String barangay;

	@Column(name = "zipcode")
	private int zipCode;

	@Column(name = "direction")
	private String direction;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;

	@Column(name = "customerid", updatable = false)
	private int customerId;

	public CustomerAddress()
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getBarangay()
	{
		return barangay;
	}

	public void setBarangay(String barangay)
	{
		this.barangay = barangay;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public int getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(int zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}

	@Override
	public String toString()
	{
		return "CustomerAddress [id=" + id + ", name=" + name + ", city=" + city + ", province=" + province
				+ ", zipCode=" + zipCode + ", direction=" + direction + ", latitude=" + latitude + ", longitude="
				+ longitude + ", customerId=" + customerId + "]";
	}

}
