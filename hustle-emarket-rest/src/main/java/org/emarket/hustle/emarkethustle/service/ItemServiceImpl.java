package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.emarket.hustle.emarkethustle.dao.BasketRepository;
import org.emarket.hustle.emarkethustle.dao.ItemRepository;
import org.emarket.hustle.emarkethustle.entity.Item;
import org.emarket.hustle.emarkethustle.entity.Store;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestItem;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService
{
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	BasketRepository basketRepository;

	@Override
	public List<Item> getItem()
	{
		List<Item> items = itemRepository.findAll();

		if(items.isEmpty())
		{
			throw new NotFoundException("ITEMS");
		}

		return items;
	}

	@Override
	public List<Item> getItemByDelisted(boolean delisted)
	{
		return itemRepository.findByDelisted(delisted);
	}

	@Override
	@Transactional
	public List<Item> getItem(GetRequestItem getRequest)
	{

		// @formatter:off
		Pageable pageable = PageRequest.of(getRequest.getPage(),
							getRequest.getSize(),
							Sort.by(Sort.Direction.DESC, getRequest.getField()));

		// @formatter:off
		List<Item> items = getRequest.getCategory() == null ?
								  itemRepository.findByDelisted(false)
								  : itemRepository.findByCategory(getRequest.getCategory(),
							      "%" + getRequest.getName() + "%", pageable);


		if(items.isEmpty())
		{
			throw new NotFoundException("ITEMS");
		}

		return items;
	}

	@Override
	public Item getItemById(int id)
	{
		Optional<Item> item = itemRepository.findById(id);

		if(item.isEmpty())
		{
			throw new NotFoundException("ITEM WITH ID: " + id);
		}
		return item.get();

	}

	@Override
	public List<Item> getItemByStore(Store store)
	{
		return itemRepository.findByStore(store);
	}


	@Override
	public void saveItem(Item item)
	{
		try
		{
			if(item.isDelisted() && item.getId() != 0)
			{
				basketRepository.deleteByItem(item);
			}
			itemRepository.save(item);


		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("SAVING ITEM");
		}

	}

	@Override
	public void deleteItem(Item item)
	{
		try
		{
			itemRepository.delete(item);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING ITEM");
		}

	}

	@Override
	public void deleteItemById(int id)
	{
		try
		{
			itemRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING ITEM WITH ID: " + id);
		}

	}

	@Override
	public void deleteItemByStore(int id)
	{
		try
		{
			itemRepository.deleteItemByStoreId(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING ITEMS IN STORE: " + id);
		}

	}

	@Override
	public void updateItemStock(int id, double value)
	{
		Item item = getItemById(id);

		item.updateInStock(value);
		saveItem(item);
	}

}
