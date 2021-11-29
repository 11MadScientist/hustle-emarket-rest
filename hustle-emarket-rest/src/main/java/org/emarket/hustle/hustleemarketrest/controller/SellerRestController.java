package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.hustleemarketrest.BcryptSecurity;
import org.emarket.hustle.hustleemarketrest.entity.Seller;
import org.emarket.hustle.hustleemarketrest.response.ErrorLoginException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.NotPermittedException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.response.UniqueErrorException;
import org.emarket.hustle.hustleemarketrest.service.SellerServiceImpl;
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
public class SellerRestController
{
	Logger log = Logger.getLogger(SellerRestController.class.getName());
	@Autowired
	private SellerServiceImpl sellerService;

	@Autowired
	private BcryptSecurity bcrypt;

	/*
	 * #######################################
	 * ########### GET SELLER ################
	 * #######################################
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
			e.printStackTrace();
			throw new NotFoundException("NO SELLER");
		}

	}

	/*
	 * #######################################
	 * ########### GET SELLER BY ID ##########
	 * #######################################
	 */
	@GetMapping("/sellers/{id}")
	public Seller getSellerById(@PathVariable int id)
	{
		try
		{
			return sellerService.getSellerById(id);

		}
		catch (Exception e)
		{
			e.printStackTrace();

			throw new NotFoundException("SELLER WITH ID: " + id);
		}
	}

	/*
	 * #######################################
	 * ############# ADD SELLER ##############
	 * #######################################
	 */
	@PostMapping("/sellers")
	public Seller addSeller(@RequestBody Seller seller)
	{
		/*
		 * don't permit creating/updating store using
		 * this endpoint
		 *
		 * set seller id to 0 to invoke insert function
		 * not update
		 *
		 * then instantiate creation date
		 *
		 * then encrypt password
		 */
		if(seller.getSellerStore() != null)
		{
			throw new NotPermittedException("SELLER ADDING/UPDATING STORE IN THIS ENDPOINT");
		}

		seller.setId(0);
		seller.setCreationDate(seller.getModifiedDate());

		if(seller.getSellerDetail() != null)
		{
			seller.getSellerDetail().setId(0);
		}

		try
		{
			seller.setPassword(bcrypt.encode(seller.getPassword()));
			return sellerService.saveSeller(seller);

		}
		catch (DataIntegrityViolationException e)
		{
			throw new UniqueErrorException("SELLER [USERNAME/EMAIL]");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * #######################################
	 * ############ UPDATE SELLER ############
	 * #######################################
	 */
	@PutMapping("/sellers")
	public Seller updateSeller(@RequestBody Seller seller)
	{
		try
		{
			/*
			 * we dont permit adding/updating store here
			 */
			if(seller.getSellerStore() != null)
			{
				throw new NotPermittedException("SELLER ADDING/UPDATING STORE IN THIS ENDPOINT");
			}

			/*
			 * Getting the password from the database and injecting it to the
			 * current seller
			 * passing the creation date to remain unchanged
			 * updating the modifiedDate
			 */

			Seller dbseller = sellerService.getSellerById(seller.getId());

			if(seller.getSellerDetail() != null)
			{
				try
				{
					seller.getSellerDetail().setId(dbseller.getSellerDetail().getId());
				}
				catch (NullPointerException e)
				{
					seller.getSellerDetail().setId(0);
				}
			}

			seller.setPassword(dbseller.getPassword());
			seller.setCreationDate(dbseller.getCreationDate());

			return sellerService.saveSeller(seller);

		}
		catch (DataIntegrityViolationException e)
		{
			throw new UniqueErrorException("SELLER [USERNAME/EMAIL]");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * #######################################
	 * ########### DELETE SELLER #############
	 * #######################################
	 */
	@DeleteMapping("/sellers/{id}")
	public ProcessConfirmation deleteSellerById(@PathVariable int id)
	{

		try
		{
			sellerService.deleteSellerById(id);

			return new ProcessConfirmation("SUCCESS", "SELLER",
					"THE CUSTOMER WITH ID:" + id + " WAS DELETED.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * #######################################
	 * ########### SELLER LOGIN ##############
	 * #######################################
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

			throw new ErrorLoginException("SELLER [EMAIL, PASSWORD]");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * #######################################
	 * ##### SELLER CHANGE PASSWORD ##########
	 * #######################################
	 */

	@PutMapping("/sellers/changePassword")
	public Seller changePassword(Seller seller)
	{
		seller.setPassword(bcrypt.encode(seller.getPassword()));
		log.info(seller.getPassword());

		if(seller.getSellerStore() != null)
		{
			seller.getSellerStore().setSeller(seller);
		}

		try
		{
			sellerService.saveSeller(seller);

			return seller;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
