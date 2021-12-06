package org.emarket.hustle.hustleemarketrest.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Basket
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "customer_id")
	private int customerId;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "item_id", updatable = false)
	private Item item;

	@Column(name = "quantity")
	private double quantity;

	@Column(name = "creation_date", updatable = false)
	private long creationDate;

	@Column(name = "modified_date")
	private long modifiedDate;

	public Basket()
	{
		modifiedDate = System.currentTimeMillis();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public double getQuantity()
	{
		return quantity;
	}

	public void setQuantity(double quantity)
	{
		this.quantity = quantity;
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
		return "Basket [id=" + id + ", item=" + item + ", quantity=" + quantity + ", creationDate=" + creationDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
