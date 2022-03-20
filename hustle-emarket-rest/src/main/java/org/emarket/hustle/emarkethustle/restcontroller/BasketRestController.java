package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Basket;
import org.emarket.hustle.emarkethustle.entity.Customer;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.BasketService;
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
public class BasketRestController
{
	@Autowired
	private BasketService basketService;

	Logger log = Logger.getLogger(BasketRestController.class.getName());

	/*
	 * #######################################
	 * ############# GET BASKET ##############
	 * #######################################
	 */

	@GetMapping("/baskets")
	public List<Basket> getCustomerBasket(@RequestBody(required = false) Customer customer)
	{
		if(customer == null)
		{
			return basketService.getBasket();
		}

		return basketService.getCustomerBasket(customer.getId());
	}

	/*
	 * #######################################
	 * ######### GET BASKET BY ID ############
	 * #######################################
	 */

	@GetMapping("/baskets/{id}")
	public Basket getBasketById(@PathVariable int id)
	{
		return basketService.getBasketById(id);
	}

	/*
	 * #######################################
	 * ############ ADD BASKET ###############
	 * #######################################
	 */

	@PostMapping("/baskets/{customerId}")
	public Basket addBasket(@PathVariable int customerId, @RequestBody Basket basket)
	{

		basketService.addBasket(basket, customerId);

		return basket;
	}

	/*
	 * #######################################
	 * ########### UPDATE BASKET #############
	 * #######################################
	 */

	@PutMapping("/baskets")
	public Basket updateBasket(@RequestBody Basket basket)
	{
		basketService.updateBasket(basket);
		return basket;
	}

	/*
	 * #######################################
	 * ######## DELETE BASKET BY ID ##########
	 * #######################################
	 */

	@DeleteMapping("/baskets/{id}")
	public ProcessConfirmation deleteBasketById(@PathVariable int id)
	{

		basketService.deleteBasketById(id);

		return new ProcessConfirmation("SUCCESS", "ITEM",
				"THE ITEM WITH ID:" + id + " WAS DELETED.");

	}

	/*
	 * #######################################
	 * #### DELETE BASKET BY CUSTOMER ########
	 * #######################################
	 */

	@DeleteMapping("/baskets/customers/{id}")
	public ProcessConfirmation deleteBasketByCustomer(@PathVariable int id)
	{
		basketService.deleteBsaketByCustomer(id);

		return new ProcessConfirmation("SUCCESS", "ALL BASKET ENTRIES",
				"FROM CUSTOMER WITH ID: " + id + " WAS DELETED.");

	}
}
