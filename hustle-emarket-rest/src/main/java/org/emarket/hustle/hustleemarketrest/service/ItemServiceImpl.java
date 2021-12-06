package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.emarket.hustle.hustleemarketrest.dao.ItemRepository;
import org.emarket.hustle.hustleemarketrest.entity.Item;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService
{
	@Autowired
	ItemRepository itemRepository;

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
	public void saveItem(Item item)
	{
		try
		{
			itemRepository.save(item);
		}
		catch (Exception e)
		{
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
	@Transactional
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

}
