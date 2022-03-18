package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.ErrorLoginException;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.response.UniqueErrorException;
import org.emarket.hustle.emarkethustle.security.BcryptSecurity;
import org.emarket.hustle.emarkethustle.service.RiderService;
import org.emarket.hustle.emarkethustle.service.ValidationService;
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
public class RiderRestController
{

	// for the bean bCrypt
	@Autowired
	private BcryptSecurity bcrypt;

//	for duplicate validation
	@Autowired
	private ValidationService validationService;

	@Autowired
	private RiderService riderService;

	/*
	 * #######################################
	 * ############# GET RIDER ###############
	 * #######################################
	 */

	@GetMapping("/riders")
	public List<Rider> getRider(@RequestBody(required = false) GetRequestUser getRequest)
	{
		if(getRequest == null)
		{
			return riderService.getRiders();
		}

		return riderService.getRiders(getRequest);
	}

	/*
	 * #######################################
	 * ######### GET RIDER BY ID #############
	 * #######################################
	 */
	@GetMapping("/riders/{id}")
	public Rider getRiderById(@PathVariable int id)
	{
		return riderService.getRiderById(id);
	}

	/*
	 * #######################################
	 * ############# ADD Rider ###############
	 * #######################################
	 */

	@PostMapping("/riders")
	public Rider addRider(@RequestBody Rider rider)
	{
		// check if the username is already taken and if the email is taken

		if(!validationService.isEmailNotTaken(rider.getRiderDetail().getEmail()))
		{
			throw new UniqueErrorException("Email ");
		}

		if(!validationService.isUsernameNotTaken(rider.getUsername()))
		{
			throw new UniqueErrorException("Username ");
		}

		rider.setId(0);

		/* encrypting password using bcrypt */
		rider.setPassword(bcrypt.encode(rider.getPassword()));
		return riderService.saveRider(rider);

	}

	/*
	 * #######################################
	 * ######## UPDATE RIDER #################
	 * #######################################
	 */

	@PutMapping("/riders")
	public Rider updateRider(@RequestBody Rider rider)
	{

		Rider dbRider = riderService.getRiderById(rider.getId());

		if(dbRider == null)
		{
			throw new NotFoundException("CUSTOMER WITH ID: " + rider.getId());
		}

		rider.setPassword(dbRider.getPassword());

		return riderService.saveRider(rider);

	}

	/*
	 * #######################################
	 * ############ DELETE RIDER #############
	 * #######################################
	 */
	@DeleteMapping("/riders/{id}")
	public ProcessConfirmation deleteRiderById(@PathVariable int id)
	{

		riderService.deleteRiderById(id);

		return new ProcessConfirmation("SUCCESS", "RIDER",
				"THE RIDER WITH ID:" + id + " WAS DELETED.");

	}

	/*
	 * #######################################
	 * ########### RIDERS LOGIN ##############
	 * #######################################
	 */

	@PostMapping("/riders/login")
	public Rider loginRider(@RequestBody Rider rider)
	{

		Rider dbRider = riderService.findRiderByUsername(rider.getUsername());

		if(bcrypt.matches(rider.getPassword(), dbRider.getPassword()))
		{
			dbRider.setStatus(true);
			riderService.saveRider(dbRider);
			return dbRider;
		}
		throw new ErrorLoginException("RIDER [Username, Password]");

	}

	/*
	 * #######################################
	 * ########## SELLER LOGOUT ##############
	 * #######################################
	 */

	@PostMapping("/riders/logout")
	public Rider logoutRider(@RequestBody Rider rider)
	{

		Rider dbRider = riderService.findRiderByUsername(rider.getUsername());

		dbRider.setStatus(false);
		riderService.saveRider(dbRider);
		return dbRider;

	}

	/*
	 * #######################################
	 * ##### SELLER CHANGE PASSWORD ##########
	 * #######################################
	 */

	@PutMapping("/riders/password")
	public Rider changePassword(@RequestBody PutRequestChangePassword changePass)
	{

		Rider dbRider = riderService.getRiderById(changePass.getId());

		if(bcrypt.matches(changePass.getPassword(), dbRider.getPassword()))
		{
			dbRider.setPassword(bcrypt.encode(changePass.getNewPassword()));

			riderService.saveRider(dbRider);
			return dbRider;
		}

		throw new FailedException("UPDATING PASSWORD");

	}

}
