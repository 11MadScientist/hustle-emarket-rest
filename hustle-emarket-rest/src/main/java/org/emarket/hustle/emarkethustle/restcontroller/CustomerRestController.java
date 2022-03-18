package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Customer;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.ErrorLoginException;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.response.UniqueErrorException;
import org.emarket.hustle.emarkethustle.security.BcryptSecurity;
import org.emarket.hustle.emarkethustle.service.CustomerService;
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
public class CustomerRestController
{
	Logger log = Logger.getLogger(CustomerRestController.class.getName());

	// for the bean bCrypt
	@Autowired
	private BcryptSecurity bcrypt;

//	for duplicate validation
	@Autowired
	private ValidationService validationService;

	@Autowired
	private CustomerService customerService;

	/*
	 * #######################################
	 * ########### GET CUSTOMER ##############
	 * #######################################
	 */

	@GetMapping("/customers")
	public List<Customer> getCustomer(@RequestBody(required = false) GetRequestUser getRequest)
	{
		if(getRequest == null)
		{
			return customerService.getCustomer();
		}

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
		// check if the username is already taken and if the email is taken

		if(!validationService.isEmailNotTaken(customer.getCustomerDetail().getEmail()))
		{
			throw new UniqueErrorException("Email ");
		}

		if(!validationService.isUsernameNotTaken(customer.getUsername()))
		{
			throw new UniqueErrorException("Username ");
		}

		/*
		 * set customer id to 0 to trigger INSERT, not UPDATE
		 * check if the customerDetail exist
		 * change id to 0 if exists
		 */
		customer.setId(0);
//		customer.setCreationDate(customer.getModifiedDate());

		/* encrypting password using bcrypt */
		customer.setPassword(bcrypt.encode(customer.getPassword()));
		return customerService.saveCustomer(customer);

	}

	/*
	 * #######################################
	 * ######## UPDATE CUSTOMER ##############
	 * #######################################
	 */

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer)
	{
		/*
		 * because we do not pass the password to the client, we need to update
		 * the password when the client wants to update their data.
		 * we can do this by getting the data first then pinning the saved password
		 * from the database to passed Customer data
		 */

		Customer dbCustomer = customerService.getCustomerById(customer.getId());

		if(dbCustomer == null)
		{
			throw new NotFoundException("CUSTOMER WITH ID: " + customer.getId());
		}

		customer.setPassword(dbCustomer.getPassword());

		return customerService.saveCustomer(customer);

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
		/*
		 * fetching Customer through username,
		 * checking the password on the returned
		 * customer if it matches the password
		 * given by the user.
		 * gives data if true, error when false
		 */

		Customer dbCustomer = customerService.findCustomerByUsername(customer.getUsername());

		if(bcrypt.matches(customer.getPassword(), dbCustomer.getPassword()))
		{
			dbCustomer.getCustomerDetail().setStatus(true);
			customerService.saveCustomer(dbCustomer);
			return dbCustomer;
		}
		throw new ErrorLoginException("CUSTOMER [Username, Password]");

	}

	/*
	 * #######################################
	 * ########## LOGOUT CUSTOMER #############
	 * #######################################
	 */

	@PostMapping("/customers/logout")
	public Customer logoutCustomer(@RequestBody Customer customer)
	{

		Customer dbCustomer = customerService.findCustomerByUsername(customer.getUsername());

		dbCustomer.getCustomerDetail().setStatus(false);
		customerService.saveCustomer(dbCustomer);
		return dbCustomer;

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

		Customer dbCustomer = customerService.getCustomerById(changePass.getId());

		if(bcrypt.matches(changePass.getPassword(), dbCustomer.getPassword()))
		{
			dbCustomer.setPassword(bcrypt.encode(changePass.getNewPassword()));

			customerService.saveCustomer(dbCustomer);
			return dbCustomer;
		}

		throw new FailedException("UPDATING PASSWORD");

	}

}
