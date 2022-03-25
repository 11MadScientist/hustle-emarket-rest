package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.ErrorLoginException;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.response.UniqueErrorException;
import org.emarket.hustle.emarkethustle.security.BcryptSecurity;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<Seller> getSeller(
			@RequestParam(name = "searchField", required = false) String searchField,
			@RequestParam(name = "searchPattern", defaultValue = "") String searchPattern,
			@RequestParam(name = "field", defaultValue = "firstName") String field)
	{
		if(searchField == null)
		{
			System.out.println("hello");
			return sellerService.getSeller();
		}
		System.out.println(searchField);
		GetRequestUser getRequest = new GetRequestUser();
		getRequest.setSearchField(searchField);
		getRequest.setSearchPattern(searchPattern);
		getRequest.setField(field);

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

		if(!validationService.isEmailNotTaken(seller.getSellerDetail().getEmail()))
		{
			throw new UniqueErrorException("Email ");
		}

		if(!validationService.isUsernameNotTaken(seller.getUsername()))
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

		try
		{
			/* creating connection from store to seller */
			seller.getStore().setId(0);
			seller.getStore().setSeller(seller);
//			seller.getStore().setCreationDate(seller.getModifiedDate());
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

		Seller dbSeller = sellerService.findSellerByUsername(seller.getUsername());

		if(bcrypt.matches(seller.getPassword(), dbSeller.getPassword()))
		{
			dbSeller.getSellerDetail().setStatus(true);
			sellerService.saveSeller(dbSeller);
			return dbSeller;
		}
		throw new ErrorLoginException("SELLER [Username, Password]");

	}

	/*
	 * #######################################
	 * ########## SELLER LOGOUT ##############
	 * #######################################
	 */

	@PostMapping("/sellers/logout")
	public Seller logoutSeller(@RequestBody Seller seller)
	{

		Seller dbSeller = sellerService.findSellerByUsername(seller.getUsername());

		dbSeller.getSellerDetail().setStatus(false);
		sellerService.saveSeller(dbSeller);
		return dbSeller;

	}

	/*
	 * #######################################
	 * ##### SELLER CHANGE PASSWORD ##########
	 * #######################################
	 */

	@PutMapping("/sellers/password")
	public Seller changePassword(@RequestBody PutRequestChangePassword changePass)
	{

		Seller dbSeller = sellerService.getSellerById(changePass.getId());

		if(bcrypt.matches(changePass.getPassword(), dbSeller.getPassword()))
		{
			dbSeller.setPassword(bcrypt.encode(changePass.getNewPassword()));

			sellerService.saveSeller(dbSeller);
			return dbSeller;
		}

		throw new FailedException("UPDATING PASSWORD");

	}

}
