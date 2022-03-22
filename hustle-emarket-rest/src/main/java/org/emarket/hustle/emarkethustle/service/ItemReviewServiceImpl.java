package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.ItemReviewRepository;
import org.emarket.hustle.emarkethustle.entity.Item;
import org.emarket.hustle.emarkethustle.entity.ItemReview;
import org.emarket.hustle.emarkethustle.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemReviewServiceImpl implements ItemReviewService
{

	@Autowired
	private ItemReviewRepository itemReviewRepository;

	@Autowired
	private ItemService itemService;

	@Autowired
	private StoreService storeService;

//	recalibrates the overall rating of the item and its store
	public void recalibrateOverallRating(Item item)
	{
		List<ItemReview> itemReviews = getItemReviewByItem(item);

		double sum = 0;

		for (ItemReview itemReview : itemReviews)
		{
			sum += itemReview.getRating();
		}

		item.setOverallReview(sum / itemReviews.size());

		Store store = item.getStore();

		List<Item> items = itemService.getItemByStore(store);
		sum = 0;

		for (Item storeItem : items)
		{
			sum += storeItem.getOverallReview();
		}
		store.setOverallRating(sum / items.size());

		storeService.saveStore(store);

	}

	@Override
	public List<ItemReview> getItemReview()
	{
		return itemReviewRepository.findAll();
	}

	@Override
	public ItemReview getItemReviewById(int id)
	{
		Optional<ItemReview> result = itemReviewRepository.findById(id);

		if(!result.isEmpty())
		{
			return result.get();
		}

		return null;
	}

	@Override
	public List<ItemReview> getItemReviewByItem(Item item)
	{
		return itemReviewRepository.findByItem(item);
	}

	@Override
	public ItemReview addItemReview(ItemReview itemReview)
	{
		itemReview.setId(0);
		recalibrateOverallRating(itemReview.getItem());
		return itemReviewRepository.save(itemReview);
	}

	@Override
	public ItemReview updateAdmin(ItemReview itemReview)
	{
		return itemReviewRepository.save(itemReview);

	}

	@Override
	public void deleteItemReviewById(int id)
	{
		itemReviewRepository.deleteById(id);

	}

}
