package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Item;
import org.emarket.hustle.emarkethustle.entity.Store;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestItem;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.ItemService;
import org.emarket.hustle.emarkethustle.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class ItemRestController
{

	Logger log = Logger.getLogger(TransactionRestController.class.getName());

	@Autowired
	private ItemService itemService;

	@Autowired
	private StoreService storeService;

	/*
	 * #######################################
	 * ############# GET ITEM ###############
	 * #######################################
	 */

	@GetMapping("/items")
	public List<Item> getItem(
			@RequestParam(name = "custom", defaultValue = "false") boolean custom,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "field", defaultValue = "price") String field,
			@RequestParam(name = "categories", required = false) List<Integer> categories)
	{
		if(custom == false)
		{
			System.out.println("hello");
			return itemService.getItem();
		}
		GetRequestItem getRequest = new GetRequestItem();
		getRequest.setName(name);
		getRequest.setField(field);
		getRequest.setCategory(categories);
		System.out.println(categories);

		return itemService.getItem(getRequest);
	}

	/*
	 * #######################################
	 * ########### GET ITEM BY ID ############
	 * #######################################
	 */

	@GetMapping("/items/{id}")
	public Item getItemById(@PathVariable int id)
	{
		return itemService.getItemById(id);
	}

	/*
	 * #######################################
	 * ######### GET ITEM BY STORE ###########
	 * #######################################
	 */

	@GetMapping("/items/stores")
	public List<Item> getItemByStore(@RequestParam Integer id)
	{

		Store store = new Store();
		store.setId(id);
		return itemService.getItemByStore(store);
	}

	/*
	 * #######################################
	 * ############## ADD ITEM ###############
	 * #######################################
	 */

	@PostMapping("/items/{id}")
	public Item addItem(@RequestBody Item item, @PathVariable int id)
	{

		Store store;
		try
		{
			store = storeService.getStoreById(id);
		}
		catch (Exception e)
		{
			throw new NotFoundException("STORE WITH ID: " + id);
		}

		store.updateItemsAdded(1);

		item.setId(0);
		item.setStore(store);

		itemService.saveItem(item);

		return item;
	}

	/*
	 * #######################################
	 * ############ UPDATE ITEM ##############
	 * #######################################
	 */

	@PutMapping("/items")
	public Item updateItem(@RequestBody Item item)
	{

		itemService.saveItem(item);

		return item;
	}

	/*
	 * #######################################
	 * ######### DELETE ITEM BY ID ###########
	 * #######################################
	 */

	@DeleteMapping("/items/{id}")
	public ProcessConfirmation deleteItemById(@PathVariable int id)
	{
		Item item = itemService.getItemById(id);
		item.getStore().updateItemsAdded(-1);

		itemService.deleteItem(item);

		return new ProcessConfirmation("SUCCESS", "ITEM",
				"THE ITEM WITH ID:" + id + " WAS DELETED.");
	}

	/*
	 * #######################################
	 * ######## DELETE ITEM BY STORE ##########
	 * #######################################
	 */

	// thinking if this really needs to be implemented

}
