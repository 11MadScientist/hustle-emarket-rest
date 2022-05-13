package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.emarket.hustle.emarkethustle.dao.BasketRepository;
import org.emarket.hustle.emarkethustle.entity.Basket;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.NotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService
{
	Logger log = Logger.getLogger(BasketServiceImpl.class.getName());

	@Autowired
	BasketRepository basketRepository;

	@Autowired
	ItemService itemService;

	/*
	 * checking if quantity is not greater than instock and quantity not less than 1
	 */
	public boolean checkQuantity(Basket basket)
	{

		if(basket.getItem().getInStock() < basket.getQuantity() || basket.getQuantity() < 0)
		{
			log.info(basket.getItem().getInStock() + ":" + basket.getQuantity());
			throw new NotPermittedException("DECLARING QUANTITY GREATER THAN INSTOCK OR LESS THAN 0");

		}
		return true;
	}

	@Override
	public List<Basket> getBasket()
	{
		List<Basket> baskets = basketRepository.findAll();

		if(baskets.isEmpty())
		{
			throw new NotFoundException("BASKETS");
		}

		return baskets;
	}

	@Transactional
	@Override
	public List<Basket> getCustomerBasket(int customerId)
	{

		List<Basket> baskets = basketRepository.findByCustomerId(customerId);

		if(baskets.isEmpty())
		{
			throw new NotFoundException("BASKET FOR CUSTOMER: " + customerId);
		}
		log.info("Customer with id: " + customerId + " has requested for get basket by customer");

		return baskets;

	}

	@Override
	public Basket getBasketById(int id)
	{
		Optional<Basket> result = basketRepository.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("BASKET WITH ID: " + id);
		}

		return result.get();
	}

	@Override
	public void addBasket(Basket basket, int customerId)
	{
		try
		{
//			dbBasket = item
			checkQuantity(basket);

			basket.setId(0);
			basket.setCustomerId(customerId);

			basketRepository.save(basket);
		}
		catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			throw new NotPermittedException("DUPLICATE ITEM IN BASKET");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("SAVING BASKET");
		}

	}

	@Override
	public void updateBasket(Basket basket)
	{
		try
		{
			checkQuantity(basket);
			basketRepository.save(basket);
		}
		catch (DataIntegrityViolationException e)
		{
			e.printStackTrace();
			throw new NotPermittedException("DUPLICATE ITEM IN BASKET");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("SAVING BASKET");
		}

	}

	@Override
	public void deleteBasket(Basket basket)
	{
		try
		{
			basketRepository.delete(basket);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING BASKET");
		}

	}

	@Override
	public void deleteBasketById(int id)
	{
		try
		{
			basketRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING BASKET WITH ID: " + id);
		}

	}

	@Transactional
	@Override
	public void deleteBsaketByCustomer(int customerId)
	{
		try
		{
			basketRepository.deleteByCustomerId(customerId);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING BASKET WITH CUSTOMER: " + customerId);
		}

	}

}
