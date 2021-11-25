package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.hustleemarketrest.BcryptSecurity;
import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
import org.emarket.hustle.hustleemarketrest.error.CustomerNotFoundException;
import org.emarket.hustle.hustleemarketrest.error.UniqueErrorException;
import org.emarket.hustle.hustleemarketrest.service.CustomerService;
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
public class CustomerRestController
{
	Logger log = Logger.getLogger(CustomerRestController.class.getName());
	@Autowired
	private BcryptSecurity bcrypt;

	@Autowired
	private CustomerService customerService;

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
			throw new CustomerNotFoundException("No customers was found");
		}
	}

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
			throw new CustomerNotFoundException("Customer with id:" + id + " not found");
		}

	}

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		customer.setId(0);

		customer.setPassword("{bcrypt}" + bcrypt.encode(customer.getPassword()));
		log.info(customer.getPassword());

		if(customer.getCustomerAddress() != null)
		{
			for (CustomerAddress address : customer.getCustomerAddress())
			{
				address.setCustomer(customer);
			}
		}

		customer.getCustomerDetail().setCustomer(customer);

		try
		{
			customerService.saveCustomer(customer);
			return customer;
		}
		catch (Exception e)
		{
			throw new UniqueErrorException("Customer [email, username] should be unique");
		}

	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer)
	{
		if(customer.getCustomerAddress() != null)
		{
			for (CustomerAddress address : customer.getCustomerAddress())
			{
				address.setCustomer(customer);
			}
		}

		try
		{
			customerService.saveCustomer(customer);
			return customer;
		}
		catch (Exception e)
		{
			throw new UniqueErrorException("Customer [email, username] should be unique");
		}

	}

	@DeleteMapping("/customers/{id}")
	public String deleteCustomerById(@PathVariable int id)
	{
		customerService.deleteCustomerById(id);
		return ("Deleted Customer with id - " + id);
	}

}
