package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Promotion;

public interface PromotionService
{

	Promotion getPromotionById(int id);

	List<Promotion> getPromotions();

	void addPromotion(Promotion promotion);

	void updatePromotion(Promotion promotion);

	void deletePromotion(Promotion promotion);

	List<Promotion> getPromotionsDisabled(boolean disabled);

}
