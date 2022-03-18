package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.ItemReviewRepository;
import org.emarket.hustle.emarkethustle.entity.ItemReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemReviewServiceImpl implements ItemReviewService
{

	@Autowired
	private ItemReviewRepository itemReviewRepository;

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
	public ItemReview addItemReview(ItemReview itemReview)
	{
		itemReview.setId(0);
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
