package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;

import org.emarket.hustle.emarkethustle.BcryptSecurity;
import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.response.ErrorLoginException;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.NotPermittedException;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.response.UniqueErrorException;
import org.emarket.hustle.emarkethustle.service.SellerServiceImpl;
import org.emarket.hustle.emarkethustle.service.ValidationServiceImpl;
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
public class SellerRestController
{
	@Autowired
	private SellerServiceImpl sellerService;

	@Autowired
	private ValidationServiceImpl validationService;

	@Autowired
	private BcryptSecurity bcrypt;

	/*
	 * #######################################
	 * ########### GET SELLER ################
	 * #######################################
	 */
	@GetMapping("/sellers")
	public List<Seller> getSeller(@RequestBody(required = false) GetRequestUser getRequest)
	{
		if(getRequest == null)
		{
			return sellerService.getSeller();
		}

		return sellerService.getSeller(getRequest);
	}

	/*
	 * #######################################
	 * ########### GET SELLER BY ID ##########
	 * #######################################
	 */
	@GetMapping("/sellers/{id}")
	public Seller getSellerById(@PathVariable int id)
	{
		return sellerService.getSellerById(id);
	}

	/*
	 * #######################################
	 * ############# ADD SELLER ##############
	 * #######################################
	 */
	@PostMapping("/sellers")
	public Seller addSeller(@RequestBody Seller seller)
	{

		// check if the username is already taken and if the email is taken

		if(validationService.isEmailTaken(seller.getSellerDetail().getEmail()))
		{
			throw new UniqueErrorException("Email ");
		}

		if(validationService.isUsernameTaken(seller.getUsername()))
		{
			throw new UniqueErrorException("Username ");
		}

		/*
		 * set seller id to 0 to invoke insert function
		 * not update
		 *
		 * then instantiate creation date
		 *
		 * then encrypt password
		 */

		seller.setId(0);
		seller.setCreationDate(seller.getModifiedDate());

		try
		{
			/* creating connection from store to seller */
			seller.getStore().setId(0);
			seller.getStore().setSeller(seller);
			seller.getStore().setCreationDate(seller.getModifiedDate());
		}
		finally
		{
			seller.setPassword(bcrypt.encode(seller.getPassword()));
		}

		return sellerService.saveSeller(seller);
	}

	/*
	 * #######################################
	 * ############ UPDATE SELLER ############
	 * #######################################
	 */
	@PutMapping("/sellers")
	public Seller updateSeller(@RequestBody Seller seller)
	{

		/*
		 * Getting the password from the database and injecting it to the
		 * current seller
		 * passing the creation date to remain unchanged
		 * updating the modifiedDate
		 */

		Seller dbseller = sellerService.getSellerById(seller.getId());

		if(seller.getSellerDetail() == null)
		{
			throw new NotFoundException("SELLER WITH ID: " + seller.getId());
		}

		seller.setPassword(dbseller.getPassword());
		seller.setCreationDate(dbseller.getCreationDate());

		if(dbseller.getStore() == null)
		{
			/* creating connection from store to seller */
			seller.getStore().setId(0);
			seller.getStore().setSeller(seller);
			seller.getStore().setCreationDate(seller.getModifiedDate());
		}
		else
		{
			seller.getStore().setCreationDate(dbseller.getStore().getCreationDate());
		}

		return sellerService.saveSeller(seller);

	}

	/*
	 * #######################################
	 * ########### DELETE SELLER #############
	 * #######################################
	 */
	@DeleteMapping("/sellers/{id}")
	public ProcessConfirmation deleteSellerById(@PathVariable int id)
	{

		sellerService.deleteSellerById(id);

		return new ProcessConfirmation("SUCCESS", "SELLER",
				"THE CUSTOMER WITH ID:" + id + " WAS DELETED.");

	}

	/*
	 * #######################################
	 * ########### SELLER LOGIN ##############
	 * #######################################
	 */

	@PostMapping("/sellers/login")
	public Seller loginSeller(@RequestBody Seller seller)
	{

		Seller dbseller = sellerService.loginSeller(seller.getUsername());

		if(bcrypt.matches(seller.getPassword(), dbseller.getPassword()))
		{
			if(seller.getSellerDetail().isAuthorized() == false)
			{
				throw new NotPermittedException("ACCESSING UNAUTHORIZED SELLER ACCOUNT");
			}

			dbseller.getSellerDetail().setStatus(true);
			return sellerService.saveSeller(dbseller);
		}
		throw new ErrorLoginException("SELLER [Email, Password]");

	}

	/*
	 * #######################################
	 * ########## SELLER LOGOUT ##############
	 * #######################################
	 */

	@PostMapping("/sellers/logout")
	public Seller logoutSeller(@RequestBody Seller seller)
	{
		seller.getSellerDetail().setStatus(false);
		sellerService.saveSeller(seller);

		throw new NotPermittedException("USE OF UNSUPPORTED ENDPOINT");

	}

	/*
	 * #######################################
	 * ##### SELLER CHANGE PASSWORD ##########
	 * #######################################
	 */

	@PutMapping("/sellers/{password}")
	public Seller changePassword(@PathVariable String password, @RequestBody Seller seller)
	{
		Seller dbSeller = sellerService.getSellerById(seller.getId());

		if(bcrypt.matches(password, dbSeller.getPassword()))
		{
			seller.setPassword(bcrypt.encode(seller.getPassword()));

			sellerService.saveSeller(seller);
			return seller;
		}

		throw new FailedException("UPDATING PASSWORD");

	}

}
