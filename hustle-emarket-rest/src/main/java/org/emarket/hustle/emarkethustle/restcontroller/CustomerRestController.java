package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Customer;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.CustomerService;
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
public class CustomerRestController
{
	Logger log = Logger.getLogger(CustomerRestController.class.getName());

	@Autowired
	private CustomerService customerService;

	/*
	 * #######################################
	 * ########### GET CUSTOMER ##############
	 * #######################################
	 */

	@GetMapping("/customers")
	public List<Customer> getCustomer(
			@RequestParam(name = "searchField", required = false) String searchField,
			@RequestParam(name = "searchPattern", defaultValue = "") String searchPattern,
			@RequestParam(name = "field", defaultValue = "firstName") String field)
	{
		if(searchField == null)
		{
			System.out.println("hello");
			return customerService.getCustomer();
		}
		GetRequestUser getRequest = new GetRequestUser();
		getRequest.setSearchField(searchField);
		getRequest.setSearchPattern(searchPattern);
		getRequest.setField(field);

		return customerService.getCustomer(getRequest);
	}

	/*
	 * #######################################
	 * ####### GET CUSTOMER BY ID ############
	 * #######################################
	 */
	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable int id)
	{
		return customerService.getCustomerById(id);
	}

	/*
	 * #######################################
	 * ########### ADD CUSTOMER ##############
	 * #######################################
	 */

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		return customerService.addCustomer(customer);
	}

	/*
	 * #######################################
	 * ######## UPDATE CUSTOMER ##############
	 * #######################################
	 */

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer)
	{
		return customerService.updateCustomer(customer);

	}

	/*
	 * #######################################
	 * ########## DELETE CUSTOMER ############
	 * #######################################
	 */
	@DeleteMapping("/customers/{id}")
	public ProcessConfirmation deleteCustomerById(@PathVariable int id)
	{

		customerService.deleteCustomerById(id);

		return new ProcessConfirmation("SUCCESS", "CUSTOMER",
				"THE CUSTOMER WITH ID:" + id + " WAS DELETED.");

	}

	/*
	 * #######################################
	 * ########## LOGIN CUSTOMER #############
	 * #######################################
	 */

	@PostMapping("/customers/login")
	public Customer loginCustomer(@RequestBody Customer customer)
	{
		return customerService.loginCustomer(customer);
	}

	/*
	 * #######################################
	 * ########## LOGOUT CUSTOMER #############
	 * #######################################
	 */

	@PostMapping("/customers/logout")
	public Customer logoutCustomer(@RequestBody Customer customer)
	{
		return customerService.logoutCustomer(customer);
	}

	/*
	 * #######################################
	 * ##### CUSTOMER CHANGE PASSWORD ########
	 * #######################################
	 */

	/*
	 * we need to put a custom mapping for changing of password
	 * because there is no method that checks if the password has
	 * already been hashed, or not. so in order to hash the new
	 * password and not hash the previous password, we need to create
	 * a new mapping
	 */

	@PutMapping("/customers/password")
	public Customer changePassword(@RequestBody PutRequestChangePassword changePass)
	{
		return customerService.changePass(changePass);
	}

}
