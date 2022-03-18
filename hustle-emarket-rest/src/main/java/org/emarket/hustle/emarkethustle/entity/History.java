package org.emarket.hustle.emarkethustle.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "history")
@JsonIdentityInfo(
		scope = History.class,
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class History
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "transaction_id", updatable = false)
	private Transaction transaction;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "store_id", updatable = false)
	private Store store;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "item_id", updatable = false)
	private Item item;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "price", updatable = false)
	private double price;

	@Column(name = "quantity", updatable = false)
	private double quantity;

	@Column(name = "status")
	private String status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_review_id")
	private ItemReview itemReview;

	@Column(name = "creation_date", updatable = false)
	private String creationDate;

	@Column(name = "modified_date")
	private String modifiedDate;

	public History()
	{
	}

	public History(int id, Transaction order, Store store, Item item, String itemName, double price, double quantity)
	{
		this.id = id;
		transaction = order;
		this.store = store;
		this.item = item;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Transaction getTransaction()
	{
		return transaction;
	}

	public void setTransaction(Transaction transaction)
	{
		this.transaction = transaction;
	}

	public Store getStore()
	{
		return store;
	}

	public void setStore(Store store)
	{
		this.store = store;
	}

	public ItemReview getItemReview()
	{
		return itemReview;
	}

	public void setItemReview(ItemReview itemReview)
	{
		this.itemReview = itemReview;
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getQuantity()
	{
		return quantity;
	}

	public void setQuantity(double quantity)
	{
		this.quantity = quantity;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
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

}
