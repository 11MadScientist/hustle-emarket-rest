package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.emarket.hustle.hustleemarketrest.dao.SellerRepository;
import org.emarket.hustle.hustleemarketrest.dao.StoreRepository;
import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService
{

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Override
	public List<Store> getStore()
	{
		return storeRepository.findAll();
	}

	@Override
	public Store getStoreById(int id)
	{
		Optional<Store> store = storeRepository.findById(id);

		if(store.isEmpty())
		{
			throw new RuntimeException("Store with id:" + id + " was not found");
		}

		return store.get();
	}

	@Override
	public Store saveStore(Store store)
	{
		storeRepository.save(store);
		store.setSeller(sellerRepository.getById(store.getSeller().getId()));

		return store;

	}

	@Override
	@Transactional
	public void updateStore(String storeName, String storeAddress, double overallRating, int itemsAdded, int id)
	{
		storeRepository.updateStore(storeName, storeAddress, overallRating, itemsAdded, id);
	}

	@Override
	public void deleteStore(Store store)
	{
		storeRepository.delete(store);

	}

	@Override
	public void deleteStoreById(int id)
	{
		storeRepository.deleteById(id);

	}

}
