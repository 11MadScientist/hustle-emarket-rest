package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Store;

public interface StoreService
{
	public List<Store> getStore();

	public Store getStoreById(int id);

	public Store saveStore(Store store);

	public void updateStore(String storeName, String storeAddress, double overallRating, int itemsAdded, int id);

	public void deleteStore(Store store);

	public void deleteStoreById(int id);
}
