package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Item;
import org.emarket.hustle.emarkethustle.entity.ItemReview;
import org.emarket.hustle.emarkethustle.service.ItemReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class ItemReviewRestController
{

	Logger log = Logger.getLogger(CustomerAddressRestController.class.getName());

	@Autowired
	ItemReviewService itemReviewService;

	@GetMapping("/item-reviews")
	public List<ItemReview> getItemReview(
			@RequestParam(name = "id", required = false) Integer id)
	{
		log.info("ItemReviews for id:" + id + " is being asked");
		if(id == null)
		{
			return itemReviewService.getItemReview();
		}
		Item item = new Item();
		item.setId(id);
		return itemReviewService.getItemReviewByItem(item);
	}

	@GetMapping("/item-reviews/{id}")
	public ItemReview getItemReviewById(@PathVariable int id)
	{
		return itemReviewService.getItemReviewById(id);
	}

	@PostMapping("/item-reviews")
	public ItemReview addItemReview(@RequestBody ItemReview itemReview)
	{

		itemReviewService.addItemReview(itemReview);

		return itemReview;
	}

}
