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
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "store")
@JsonIdentityInfo(
		scope = Store.class,
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Store
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "store_address")
	private String storeAddress;

	@Column(name = "overall_rating")
	private double overallRating;

//	statistical data
	@Transient
	private double overallStockSold = 0;

	@Column(name = "documents")
	private String documents;

	@Column(name = "items_added")
	private int itemsAdded;

	@OneToOne(cascade = { CascadeType.DETACH,
			CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "seller_id", updatable = false)
	private Seller seller;

//	@JsonManagedReference
	@OneToMany(
			cascade = CascadeType.ALL,
			mappedBy = "store")
	private List<Item> items;

	@Column(name = "creation_date", updatable = false)
	private String creationDate;

	@Column(name = "modified_date", updatable = false)
	private String modifiedDate;

	public Store()
	{
	}

	/*
	 * #######################################
	 * ######### CUSTOMIZED METHODS ##########
	 * #######################################
	 */

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

	public double getOverallStockSold()
	{
		if(overallStockSold == 0 && items != null)
		{
			for (int i = 0; i < items.size(); i++)
			{
				overallStockSold += items.get(i).getStockSold();
			}
		}

		return overallStockSold;
	}

	public String getDocuments()
	{
		return documents;
	}

	public void setDocuments(String documents)
	{
		this.documents = documents;
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

	public List<Item> getItems()
	{
		return items;
	}

	public void setItems(List<Item> items)
	{
		this.items = items;
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
		return "Store [id=" + id + ", storeName=" + storeName + ", storeAddress=" + storeAddress + ", overallRating="
				+ overallRating + ", documents=" + documents + ", itemsAdded=" + itemsAdded + ", seller=" + seller
				+ ", items=" + items + ", creationDate=" + creationDate + ", modifiedDate=" + modifiedDate + "]";
	}

	/*
	 * #######################################
	 * ######### CUSTOMIZED METHODS ##########
	 * #######################################
	 */

	public void updateItemsAdded(int update)
	{
		itemsAdded += update;
	}

}
