package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.BcryptSecurity;
import org.emarket.hustle.hustleemarketrest.entity.Seller;
import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.NotPermittedException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	 * #######################################
	 * ############ GET STORE ################
	 * #######################################
	 */

	@GetMapping("/stores")
	public List<Store> getStore()
	{
		try
		{
			return storeService.getStore();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * #######################################
	 * ########### GET STORE BY ID ###########
	 * #######################################
	 */

	@GetMapping("/stores/{id}")
	public Store getStoreById(@PathVariable int id)
	{
		try
		{
			return storeService.getStoreById(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * #######################################
	 * ############# ADD STORE ###############
	 * #######################################
	 */

	@PostMapping("/stores")
	public Store addStore(@RequestBody Seller seller)
	{
		/*
		 * we pass the Seller when creating Store to confirm that
		 * the seller is registered.
		 * and to establish connection.
		 */
		Store store = seller.getSellerStore();

		store.setSeller(seller);
		store.setCreationDate(store.getModifiedDate());

		try
		{
			return storeService.saveStore(store);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new NotPermittedException("SELLER CREATING MORE THAN ONE STORE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * #######################################
	 * ########### UPDATE STORE ##############
	 * #######################################
	 */

	@PutMapping("/stores")
	public Store updateStore(@RequestBody Store store)
	{
		if(store.getId() == 0)
		{
			throw new NotPermittedException("CREATING STORE IN THIS ENDPOINT");
		}

		try
		{
			storeService.updateStore(store.getStoreName(), store.getStoreAddress(), store.getOverallRating(),
					store.getItemsAdded(), store.getId());

			return store;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * #######################################
	 * ######### DELETE STORE BY ID ##########
	 * #######################################
	 */
	@DeleteMapping("/stores/{id}")
	public ProcessConfirmation deleteStoreById(@PathVariable int id)
	{

		try
		{
			Store store = storeService.getStoreById(id);

			if(store == null)
			{
				throw new NotFoundException("STORE WITH ID: " + id);
			}

			/*
			 * severe the ties between the store and the seller
			 * so we can safely delete the store
			 */
			store.getSeller().setSellerStore(null);

			storeService.deleteStoreById(id);

			return new ProcessConfirmation("SUCCESS", "SELLER",
					"THE CUSTOMER WITH ID:" + id + " WAS DELETED.");
		}
		catch (Exception e)
		{
			e.printStackTrace();

			throw new RuntimeException(e);
		}

	}

}
