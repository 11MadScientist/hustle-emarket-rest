package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Store;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestStore;

public interface StoreService
{
	public List<Store> getStore();

	public List<Store> getStore(GetRequestStore getRequest);

	public Store getStoreById(int id);

	public Store saveStore(Store store);

	public void deleteStore(Store store);

	public void deleteStoreById(int id);

	public List<Store> getStoreByOverallStockSold();
}
