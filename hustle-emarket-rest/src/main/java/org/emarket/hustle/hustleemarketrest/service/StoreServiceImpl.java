package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.emarket.hustle.hustleemarketrest.controller.StoreRestController;
import org.emarket.hustle.hustleemarketrest.dao.StoreRepository;
import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.emarket.hustle.hustleemarketrest.response.FailedException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService
{

	@Autowired
	StoreRepository storeRepository;

	Logger log = Logger.getLogger(StoreRestController.class.getName());

	@Override
	public List<Store> getStore()
	{
		List<Store> stores = storeRepository.findAll();

		if(stores.isEmpty())
		{
			throw new NotFoundException("STORES");
		}

		return stores;
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
		try
		{
			return storeRepository.save(store);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING STORE");
		}
	}

	@Override
	public void deleteStore(Store store)
	{
		try
		{
			storeRepository.delete(store);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING STORE");
		}

	}

	@Override
	public void deleteStoreById(int id)
	{
		try
		{
			storeRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING STORE WITH ID: " + id);
		}
	}

}
