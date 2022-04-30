package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.RiderService;
import org.jboss.logging.Logger;
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

	Logger log = Logger.getLogger(RiderRestController.class);
	// for the bean bCrypt

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
		return riderService.addRider(rider);

	}

	/*
	 * #######################################
	 * ######## UPDATE RIDER #################
	 * #######################################
	 */

	@PutMapping("/riders")
	public Rider updateRider(@RequestBody Rider rider)
	{

		return riderService.updateRider(rider);

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
	 * ######### RIDER AVAILABLE #############
	 * #######################################
	 */
	@PostMapping("/riders/available")
	public Rider availableRider(@RequestBody Rider rider)
	{
		return riderService.availableRider(rider);
	}

	/*
	 * #######################################
	 * ########### RIDERS LOGIN ##############
	 * #######################################
	 */

	@PostMapping("/riders/login")
	public Rider loginRider(@RequestBody Rider rider)
	{

		return riderService.loginRider(rider);

	}

	/*
	 * #######################################
	 * ########### RIDER LOGOUT ##############
	 * #######################################
	 */

	@PostMapping("/riders/logout")
	public Rider logoutRider(@RequestBody Rider rider)
	{
		return riderService.logoutRider(rider);
	}

	/*
	 * #######################################
	 * ##### SELLER CHANGE PASSWORD ##########
	 * #######################################
	 */

	@PutMapping("/riders/password")
	public Rider changePassword(@RequestBody PutRequestChangePassword changePass)
	{
		return riderService.changePass(changePass);
	}

}
