package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.StoreRepository;
import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService
{

	@Autowired
	StoreRepository storeRepository;

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
	public void saveStore(Store store)
	{
		storeRepository.save(store);

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
