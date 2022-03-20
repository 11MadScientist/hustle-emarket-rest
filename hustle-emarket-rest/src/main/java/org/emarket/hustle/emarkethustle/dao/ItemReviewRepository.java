package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Item;
import org.emarket.hustle.emarkethustle.entity.ItemReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemReviewRepository extends JpaRepository<ItemReview, Integer>
{
	public List<ItemReview> findByItem(Item item);
}
