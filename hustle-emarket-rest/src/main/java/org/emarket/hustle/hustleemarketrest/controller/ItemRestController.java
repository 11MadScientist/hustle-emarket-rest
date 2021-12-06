package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Item;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.ItemService;
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
@RequestMapping("${api.basePath}")
public class ItemRestController
{

	@Autowired
	private ItemService itemService;

	/*
	 * #######################################
	 * ############# GET ITEM ###############
	 * #######################################
	 */

	@GetMapping("/items")
	public List<Item> getItem()
	{
		return itemService.getItem();
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
	 * ############## ADD ITEM ###############
	 * #######################################
	 */

	@PostMapping("/items/{id}")
	public Item addItem(@RequestBody Item item, @PathVariable int id)
	{
		item.setId(0);
		item.getStore().setId(id);
		item.setCreationDate(item.getModifiedDate());

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
		itemService.deleteItemById(id);

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
