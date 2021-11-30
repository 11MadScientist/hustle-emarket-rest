package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.hustleemarketrest.BcryptSecurity;
import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
import org.emarket.hustle.hustleemarketrest.response.ErrorLoginException;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.response.UniqueErrorException;
import org.emarket.hustle.hustleemarketrest.service.CustomerService;
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
public class CustomerRestController
{
	Logger log = Logger.getLogger(CustomerRestController.class.getName());

	// for the bean bCrypt
	@Autowired
	private BcryptSecurity bcrypt;

	@Autowired
	private CustomerService customerService;

	/*
	 * #######################################
	 * ########### GET CUSTOMER ##############
	 * #######################################
	 */

	@GetMapping("/customers")
	public List<Customer> getCustomer()
	{

		// check if the customer is retrieved/found
		try
		{
			return customerService.getCustomer();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new NotFoundException("NO CUSTOMERS");
		}
	}

	/*
	 * #######################################
	 * ####### GET CUSTOMER BY ID ############
	 * #######################################
	 */
	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable int id)
	{

		// check if the customer is retrieved/found
		try
		{
			return customerService.getCustomerById(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new NotFoundException("CUSTOMER WITH ID: " + id);
		}

	}

	/*
	 * #######################################
	 * ########### ADD CUSTOMER ##############
	 * #######################################
	 */

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		/*
		 * set customer id to 0 to trigger INSERT, not UPDATE
		 * check if the customerDetail exist
		 * change id to 0 if exists
		 */
		customer.setId(0);
		customer.setCreationDate(customer.getModifiedDate());

		if(customer.getCustomerDetail() != null)
		{
			customer.getCustomerDetail().setId(0);
		}

		/*
		 * encrypting password using bcrypt
		 */

		try
		{
			customer.setPassword(bcrypt.encode(customer.getPassword()));
			return customerService.saveCustomer(customer);

		}
		catch (DataIntegrityViolationException e)
		{
			throw new UniqueErrorException("CUSTOMER [USERNAME/EMAIL]");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

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
		 *
		 * stop customer from creating more than one customer detail
		 */

		try
		{
			Customer dbCustomer = customerService.getCustomerById(customer.getId());

			if(customer.getCustomerDetail() != null)
			{
				try
				{
					customer.getCustomerDetail().setId(dbCustomer.getCustomerDetail().getId());
				}
				catch (NullPointerException e)
				{
					customer.getCustomerDetail().setId(0);
				}
			}
			customer.setPassword(dbCustomer.getPassword());
			customer.setCreationDate(dbCustomer.getCreationDate());

			return customerService.saveCustomer(customer);

		}
		catch (DataIntegrityViolationException e)
		{
			throw new UniqueErrorException("CUSTOMER [USERNAME/EMAIL]");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/*
	 * #######################################
	 * ########## DELETE CUSTOMER ############
	 * #######################################
	 */
	@DeleteMapping("/customers/{id}")
	public ProcessConfirmation deleteCustomerById(@PathVariable int id)
	{
		try
		{
			customerService.deleteCustomerById(id);

			return new ProcessConfirmation("SUCCESS", "CUSTOMER",
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
	 * ########## LOGIN CUSTOMER #############
	 * #######################################
	 */

	@PostMapping("/customers/login")
	public Customer loginCustomer(@RequestBody Customer customer)
	{
		/**
		 *
		 * fetching Customer through username,
		 * checking the password on the returned
		 * customer if it matches the password
		 * given by the user.
		 * gives data if true, error when false
		 */
		try
		{
			Customer dbCustomer = customerService.loginCustomer(customer.getUsername());

			if(bcrypt.matches(customer.getPassword(), dbCustomer.getPassword()))
			{
				return dbCustomer;
			}
			throw new ErrorLoginException("CUSTOMER [Email, Password]");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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

	@PutMapping("/customers/changePassword")
	public Customer changePassword(Customer customer)
	{
		customer.setPassword(bcrypt.encode(customer.getPassword()));
		log.info(customer.getPassword());

		if(customer.getCustomerAddress() != null)
		{
			for (CustomerAddress address : customer.getCustomerAddress())
			{
				address.setCustomer(customer);
			}
		}

		if(customer.getCustomerDetail() != null)
		{
			customer.getCustomerDetail().setCustomer(customer);
		}

		try
		{
			customerService.saveCustomer(customer);
			return customer;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("Saving new Password Failed");
		}
	}

}
