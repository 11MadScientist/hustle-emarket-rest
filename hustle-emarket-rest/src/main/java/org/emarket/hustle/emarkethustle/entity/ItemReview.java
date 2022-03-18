package org.emarket.hustle.emarkethustle.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "item_review")
@JsonIdentityInfo(
		scope = ItemReview.class,
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
public class ItemReview
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private int id;

	@Column(name = "rating")
	private double rating;

	@Column(name = "review")
	private String review;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	private Item item;

	@Column(name = "creation_date", updatable = false)
	private String creationDate;

	@Column(name = "modified_date")
	private String modifiedDate;

	public ItemReview()
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

	public double getRating()
	{
		return rating;
	}

	public void setRating(double rating)
	{
		this.rating = rating;
	}

	public String getReview()
	{
		return review;
	}

	public void setReview(String review)
	{
		this.review = review;
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
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
		return "ItemReview [id=" + id + ", rating=" + rating + ", review=" + review + ", item=" + item
				+ ", creationDate=" + creationDate + ", modifiedDate=" + modifiedDate + "]";
	}

}
