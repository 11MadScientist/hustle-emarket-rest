package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Favourite;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class FavouriteRestController
{
	@Autowired
	private FavouriteService favouriteService;

	Logger log = Logger.getLogger(FavouriteRestController.class.getName());

	/*
	 * #######################################
	 * #### GET FAVOURITE BY CUSTOMER ID #####
	 * #######################################
	 */

	@GetMapping("/favourites")
	public List<Favourite> getFavouriteByCustomer(@RequestParam int id)
	{
		return favouriteService.getFavouritesByCustomerId(id);
	}

	/*
	 * #######################################
	 * ####### GET FAVOURITE BY ID ###########
	 * #######################################
	 */

	@GetMapping("/favourites/{id}")
	public Favourite getFavouriteById(@PathVariable int id)
	{
		return favouriteService.getFavourite(id);
	}

	/*
	 * #######################################
	 * ######### ADD FAVOURITE ###############
	 * #######################################
	 */

	@PostMapping("/favourites")
	public Favourite addFavourite(@RequestBody Favourite favourite)
	{

		favouriteService.addFavourite(favourite);

		return favourite;
	}

	/*
	 * #######################################
	 * ######## DELETE FAVOURITE BY ID #######
	 * #######################################
	 */

	@DeleteMapping("/favourites/{id}")
	public ProcessConfirmation deleteFavouriteById(@PathVariable int id)
	{

		favouriteService.deleteFavoriteById(id);

		return new ProcessConfirmation("SUCCESS", "FAVOURITE",
				"THE FAVOURITE WITH ID:" + id + " WAS DELETED.");

	}

}
