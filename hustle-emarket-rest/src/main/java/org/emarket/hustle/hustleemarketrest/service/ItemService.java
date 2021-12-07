package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.GetRequest;
import org.emarket.hustle.hustleemarketrest.entity.Item;

public interface ItemService
{
	public List<Item> getItem();

	public List<Item> getItem(GetRequest getRequest);

	public Item getItemById(int id);

	public void saveItem(Item item);

	public void deleteItem(Item item);

	public void deleteItemById(int id);

	public void deleteItemByStore(int id);

}
