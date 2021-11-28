package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.hustleemarketrest.BcryptSecurity;
import org.emarket.hustle.hustleemarketrest.entity.Seller;
import org.emarket.hustle.hustleemarketrest.error.CustomerNotFoundException;
import org.emarket.hustle.hustleemarketrest.service.SellerServiceImpl;
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
public class SellerRestController
{
	Logger log = Logger.getLogger(SellerRestController.class.getName());
	@Autowired
	private SellerServiceImpl sellerService;

	@Autowired
	private BcryptSecurity bcrypt;

	/*
	 * BASIC CRUD OPERATIONS
	 *
	 */

	@GetMapping("/sellers")
	public List<Seller> getSeller()
	{
		try
		{
			return sellerService.getSeller();
		}
		catch (Exception e)
		{
			throw new RuntimeException("No sellers were found");
		}

	}

	@GetMapping("/sellers/{id}")
	public Seller getSellerById(@PathVariable int id)
	{

		return sellerService.getSellerById(id);
	}

	@PostMapping("/sellers")
	public Seller addSeller(@RequestBody Seller seller)
	{
		seller.setId(0);

		/*
		 * we appoint the seller as the store's seller
		 */

		if(seller.getSellerStore() != null)
		{
			log.info("inside if statement getSellerStore() != null");
			seller.getSellerStore().setSeller(seller);
		}
		log.info("outside if statement getSellerStore() != null");

		try
		{
			seller.setPassword(bcrypt.encode(seller.getPassword()));
			sellerService.saveSeller(seller);
			return seller;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@PutMapping("/sellers")
	public Seller updateSeller(@RequestBody Seller seller)
	{
		try
		{
			/*
			 * Getting the password from the database and injecting it to the
			 * current seller
			 * updating the modifiedDate
			 */

			Seller dbseller = sellerService.getSellerById(seller.getId());

			seller.setPassword(dbseller.getPassword());

			seller.setCreationDate(dbseller.getCreationDate());

			/*
			 * we appoint the seller as the store's seller
			 */
			if(seller.getSellerStore() != null)
			{

				seller.getSellerStore().setSeller(seller);
			}

			sellerService.saveSeller(seller);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

		return seller;
	}

	@DeleteMapping("/sellers/{id}")
	public String deleteSellerById(@PathVariable int id)
	{
		sellerService.deleteSellerById(id);

		return ("Deleted Seller with id:" + id);
	}

	/*
	 * Seller login endpoint
	 */
	@PostMapping("/sellers/login")
	public Seller loginSeller(@RequestBody Seller seller)
	{
		try
		{
			Seller dbSeller = sellerService.loginSeller(seller.getUsername());

			/*
			 * Checking if the encrypted password matches
			 */

			if(bcrypt.matches(seller.getPassword(), dbSeller.getPassword()))
			{
				return dbSeller;
			}

			throw new CustomerNotFoundException("Seller [Email, Password] does not match.");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new CustomerNotFoundException("No Seller with Found");
		}
	}

}
