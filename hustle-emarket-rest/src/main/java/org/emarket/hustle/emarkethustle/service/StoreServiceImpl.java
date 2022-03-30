package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.algorithms.QuickSort;
import org.emarket.hustle.emarkethustle.dao.StoreRepository;
import org.emarket.hustle.emarkethustle.entity.Store;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestStore;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService
{

	Logger log = Logger.getLogger(StoreServiceImpl.class.getName());

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	QuickSort algorithms;

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
	public List<Store> getStore(GetRequestStore getRequest)
	{
		// @formatter:off
		Pageable pageable = PageRequest.of(getRequest.getPage(),
							getRequest.getSize(),
							Sort.by(Sort.Direction.ASC, getRequest.getField()));


		Slice<Store> slicedStores = null;

		// @formatter:off
		if(getRequest.getSearchField().equals("storeName"))
		{
			slicedStores = storeRepository.findStoreByStoreNameLike
					("%"+getRequest.getSearchPattern()+"%", pageable);
		}

		else if(getRequest.getSearchField().equals("storeAddress"))
		{
			slicedStores = storeRepository.findStoreByStoreAddressLike
					("%"+getRequest.getSearchPattern()+"%", pageable);
		}

//		get store by overall stock sold
		else if(getRequest.getSearchField().equals("overallStock"))
		{
			List<Store> stores = storeRepository.findStoreByFields(
					getRequest.isAuthorized(),
					getRequest.isProhibited(),
					getRequest.getStoreAddress(),
					getRequest.getSearchPattern());

//			using quicksort to sort stores
			algorithms.sortStoreByStockSold(stores, 0, stores.size());

			return stores;
		}


		if(!slicedStores.isEmpty())
		{
			return slicedStores.getContent();

		}
		throw new NotFoundException("STORE");


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

	@Override
	public int countStores()
	{
		return storeRepository.countStores();
	}





}
