package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Basket;

public interface BasketService
{
	public List<Basket> getBasket();

	public List<Basket> getCustomerBasket(int customerId);

	public Basket getBasketById(int id);

	public void addBasket(Basket basket, int customerId);

	public void updateBasket(Basket basket);

	public void deleteBasket(Basket basket);

	public void deleteBasketById(int id);

	public void deleteBsaketByCustomer(int customerId);

}
