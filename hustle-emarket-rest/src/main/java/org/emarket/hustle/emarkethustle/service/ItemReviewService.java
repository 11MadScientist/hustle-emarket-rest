package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.ItemReview;

public interface ItemReviewService
{
	public List<ItemReview> getItemReview();

	public ItemReview getItemReviewById(int id);

	public ItemReview addItemReview(ItemReview itemReview);

	public ItemReview updateAdmin(ItemReview itemReview);

	public void deleteItemReviewById(int id);
}
