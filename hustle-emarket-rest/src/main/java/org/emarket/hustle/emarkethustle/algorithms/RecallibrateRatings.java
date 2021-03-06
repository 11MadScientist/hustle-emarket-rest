package org.emarket.hustle.emarkethustle.algorithms;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Item;
import org.emarket.hustle.emarkethustle.entity.ItemReview;
import org.emarket.hustle.emarkethustle.entity.Store;
import org.emarket.hustle.emarkethustle.service.ItemReviewService;
import org.emarket.hustle.emarkethustle.service.ItemService;
import org.emarket.hustle.emarkethustle.service.StoreService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class RecallibrateRatings
{
	@Autowired
	ItemReviewService itemReviewService;
	Logger log = Logger.getLogger(RecallibrateRatings.class.getName());

	@Autowired
	private ItemService itemService;

	@Autowired
	private StoreService storeService;

//	recalibrates the overall rating of the item and its store
	public void recalibrateOverallRating(Item item)
	{
		List<ItemReview> itemReviews = itemReviewService.getItemReviewByItem(item);

		log.info("recallibrating");
		double sum = 0;

		for (ItemReview itemReview : itemReviews)
		{
			sum += itemReview.getRating();
		}

		item.setOverallReview(sum / itemReviews.size());

		Store store = item.getStore();

		List<Item> items = itemService.getItemByStore(store);
		sum = 0;
		log.info("here1");
		for (Item storeItem : items)
		{
			sum += storeItem.getOverallReview();
		}
		log.info("here2");
		store.setOverallRating(sum / items.size());

		log.info("here3");
		storeService.saveStore(store);

	}

}
