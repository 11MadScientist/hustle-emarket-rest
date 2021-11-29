package org.emarket.hustle.hustleemarketrest.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "store")
@JsonInclude(Include.NON_EMPTY)
public class Store
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "store_address")
	private String storeAddress;

	@Column(name = "overall_rating")
	private double overallRating;

	@Column(name = "items_added")
	private int itemsAdded;

	@JsonManagedReference
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REFRESH },
			fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_id")
	private Seller seller;

	@Column(name = "creation_date")
	private long creationDate;

	@Column(name = "modified_date")
	private long modifiedDate;

	public Store()
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

	public String getStoreName()
	{
		return storeName;
	}

	public void setStoreName(String storeName)
	{
		this.storeName = storeName;
	}

	public String getStoreAddress()
	{
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress)
	{
		this.storeAddress = storeAddress;
	}

	public double getOverallRating()
	{
		return overallRating;
	}

	public void setOverallRating(double overallRating)
	{
		this.overallRating = overallRating;
	}

	public int getItemsAdded()
	{
		return itemsAdded;
	}

	public void setItemsAdded(int itemsAdded)
	{
		this.itemsAdded = itemsAdded;
	}

	public Seller getSeller()
	{
		return seller;
	}

	public void setSeller(Seller seller)
	{
		this.seller = seller;
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
		return "Store [id=" + id + ", storeName=" + storeName + ", storeAddress=" + storeAddress + ", overallRating="
				+ overallRating + ", itemsAdded=" + itemsAdded + ", seller=" + seller + ", creationDate=" + creationDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

}
