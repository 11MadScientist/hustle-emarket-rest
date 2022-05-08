package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Item;
import org.emarket.hustle.emarkethustle.entity.Store;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestItem;

public interface ItemService
{
	public List<Item> getItem();

	public List<Item> getItem(GetRequestItem getRequest);

	public Item getItemById(int id);

	public void saveItem(Item item);

	public void deleteItem(Item item);

	public void deleteItemById(int id);

	public void deleteItemByStore(int id);

	public List<Item> getItemByStore(Store store);

	List<Item> getItemByDelisted(boolean delisted);

	public void updateItemStockStockSold(int id, double value);

	Item delistItem(int id);

	Item enlistItem(int id);

}
