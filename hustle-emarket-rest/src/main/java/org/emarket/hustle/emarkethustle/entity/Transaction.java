package org.emarket.hustle.emarkethustle.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.emarket.hustle.emarkethustle.response.NotPermittedException;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "transaction")
@JsonIdentityInfo(
		scope = Transaction.class,
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class Transaction
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "customer_id", updatable = false)
	private Customer customer;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "customer_address_id", updatable = false)
	private CustomerAddress customerAddress;

	@Column(name = "station")
	private String station;

	@Column(name = "status")
	private String status;

	@Column(name = "note")
	private String note;

//	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL,
			mappedBy = "transaction")
	private List<History> histories;

	@Column(name = "sub_total")
	private double subTotal;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "promotion", updatable = false)
	private Promotion promotion;

	@Column(name = "service_fee")
	private double serviceFee;

	@Column(name = "delivery_fee")
	private double deliveryFee;

	@Column(name = "grand_total")
	private double grandTotal;

	@Column(name = "order_type")
	private String orderType;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "rider_id")
	private Rider rider;

	@Column(name = "scheduled_time")
	private String scheduledTime;

	@Column(name = "delivered_time")
	private String deliveredTime;

	@Column(name = "creation_date")
	private String creationDate;

	@Column(name = "modified_date")
	private String modifiedDate;

	public Transaction()
	{
		subTotal = 0;
	}

	/*
	 * ####################################################
	 * ################# CUSTOM METHODS ###################
	 * ####################################################
	 */

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public CustomerAddress getCustomerAddress()
	{
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddress customerAddress)
	{
		this.customerAddress = customerAddress;
	}

	public String getStation()
	{
		return station;
	}

	public void setStation(String station)
	{
		this.station = station;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public List<History> getHistories()
	{
		return histories;
	}

	public void setHistories(List<History> histories)
	{
		this.histories = histories;
	}

	public double getSubTotal()
	{
		return subTotal;
	}

	public void setSubTotal(double subTotal)
	{
		this.subTotal = subTotal;
	}

	public Promotion getPromotion()
	{
		return promotion;
	}

	public void setPromotion(Promotion promotion)
	{
		this.promotion = promotion;
	}

	public double getServiceFee()
	{
		return serviceFee;
	}

	public void setServiceFee(double serviceFee)
	{
		this.serviceFee = serviceFee;
	}

	public double getDeliveryFee()
	{
		return deliveryFee;
	}

	public void setDeliveryFee(double deliveryFee)
	{
		this.deliveryFee = deliveryFee;
	}

	public double getGrandTotal()
	{
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal)
	{
		this.grandTotal = grandTotal;
	}

	public String getOrderType()
	{
		return orderType;
	}

	public void setOrderType(String orderType)
	{
		this.orderType = orderType;
	}

	public Rider getRider()
	{
		return rider;
	}

	public void setRider(Rider rider)
	{
		this.rider = rider;
	}

	public String getScheduledTime()
	{
		return scheduledTime;
	}

	public void setScheduledTime(String scheduledTime)
	{
		this.scheduledTime = scheduledTime;
	}

	public String getDeliveredTime()
	{
		return deliveredTime;
	}

	public void setDeliveredTime(String deliveredTime)
	{
		this.deliveredTime = deliveredTime;
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

	@JsonIgnore
	public void addHistory(History history)
	{
		if(histories == null)
		{
			histories = new ArrayList<>();

		}
		histories.add(history);
	}

	@JsonIgnore
	public void addSubTotal(double total)
	{
		if(total < 0)
		{
			throw new NotPermittedException("ADDING NEGATIVE NUMBER TO GRAND TOTAL");
		}
		subTotal += total;

	}

	@JsonIgnore
	public void setGrandTotal()
	{
		grandTotal = deliveryFee + serviceFee + subTotal;
	}

}
