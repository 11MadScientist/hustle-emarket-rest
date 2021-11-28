package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.BcryptSecurity;
import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.emarket.hustle.hustleemarketrest.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emarket-hustle")
public class StoreRestController
{
	@Autowired
	public StoreService storeService;

	@Autowired
	public BcryptSecurity bcrypt;

	/*
	 * BASIC CRUD OPERATIONS
	 *
	 */

	@GetMapping("/stores")
	public List<Store> getSeller()
	{
		try
		{
			return storeService.getStore();
		}
		catch (Exception e)
		{
			throw new RuntimeException("No stores were found");
		}

	}

	@GetMapping("/stores/{id}")
	public Store getSellerById(@PathVariable int id)
	{
		return storeService.getStoreById(id);
	}

	@PostMapping("/stores")
	public Store addSeller(@RequestBody Store store)
	{
		store.setId(0);

		try
		{
			storeService.saveStore(store);
			return store;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

	}

	@PutMapping("/stores")
	public Store updateSeller(@RequestBody Store store)
	{
		try
		{
			store.setModifiedDate(System.currentTimeMillis());
			storeService.saveStore(store);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

		return store;
	}

	@DeleteMapping("/stores/{id}")
	public String deleteStoreById(@PathVariable int id)
	{

		storeService.deleteStoreById(id);

		return ("Deleted Store with id: " + id);
	}

}
