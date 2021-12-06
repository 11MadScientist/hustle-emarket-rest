package org.emarket.hustle.hustleemarketrest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer_address")
public class CustomerAddress
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "city")
	private String city;

	@Column(name = "province")
	private String province;

	@Column(name = "zip_code")
	private int zipCode;

	@Column(name = "direction")
	private String direction;

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

	@Override
	public String toString()
	{
		return "CustomerAddress [id=" + id + ", name=" + name + ", city=" + city + ", province=" + province
				+ ", zipCode=" + zipCode + ", direction=" + direction + "]";
	}

}
