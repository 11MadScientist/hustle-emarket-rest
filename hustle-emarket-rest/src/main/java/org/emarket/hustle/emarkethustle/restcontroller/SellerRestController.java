package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.SellerServiceImpl;
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
		return sellerService.addSeller(seller);
	}

	/*
	 * #######################################
	 * ############ UPDATE SELLER ############
	 * #######################################
	 */
	@PutMapping("/sellers")
	public Seller updateSeller(@RequestBody Seller seller)
	{

		return sellerService.updateSeller(seller);

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
		return sellerService.loginSeller(seller);
	}

	/*
	 * #######################################
	 * ########## SELLER LOGOUT ##############
	 * #######################################
	 */

	@PostMapping("/sellers/logout")
	public Seller logoutSeller(@RequestBody Seller seller)
	{

		return sellerService.logoutSeller(seller);

	}

	/*
	 * #######################################
	 * ##### SELLER CHANGE PASSWORD ##########
	 * #######################################
	 */

	@PutMapping("/sellers/password")
	public Seller changePassword(@RequestBody PutRequestChangePassword changePass)
	{

		return sellerService.changePass(changePass);

	}

}
