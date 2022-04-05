package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.PromotionRepository;
import org.emarket.hustle.emarkethustle.entity.Promotion;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService
{

	@Autowired
	private PromotionRepository promotionRepository;

	@Override
	public Promotion getPromotionById(int id)
	{
		try
		{

			Optional<Promotion> promotion = promotionRepository.findById(id);

			if(promotion.isEmpty())
			{
				return null;
			}

			return promotion.get();
		}
		catch (Exception e)
		{
			throw new NotFoundException("PROMOTIONS");
		}
	}

	@Override
	public List<Promotion> getPromotions()
	{
		try
		{
			return promotionRepository.findAllByOrderByIdDesc();
		}
		catch (Exception e)
		{
			throw new NotFoundException("PROMOTIONS");
		}
	}

	@Override
	public List<Promotion> getPromotionsDisabled(boolean disabled)
	{
		return promotionRepository.findByDisabledOrderByIdDesc(disabled);
	}

	@Override
	public void addPromotion(Promotion promotion)
	{
		promotion.setId(0);
		promotionRepository.save(promotion);

	}

	@Override
	public void updatePromotion(Promotion promotion)
	{
		promotionRepository.save(promotion);

	}

	@Override
	public void deletePromotion(Promotion promotion)
	{
		promotionRepository.delete(promotion);

	}

}
