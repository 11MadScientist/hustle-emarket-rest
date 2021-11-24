package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
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
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getCustomer()
	{
		List<Customer> customers = customerService.getCustomer();

		return customers;
	}

	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable int id)
	{
		Customer customer = customerService.getCustomerById(id);

		return customer;
	}

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		customer.setId(0);

		if(customer.getCustomerAddress() != null)
		{
			for (CustomerAddress address : customer.getCustomerAddress())
			{
				address.setCustomer(customer);
			}
		}

		customer.getCustomerDetail().setCustomer(customer);

		customerService.saveCustomer(customer);

		return customer;
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
		customerService.saveCustomer(customer);
		customer.getCustomerAddress();
		return customer;
	}

	@DeleteMapping("/customers/{id}")
	public String deleteCustomerById(@PathVariable int id)
	{
		customerService.deleteCustomerById(id);
		return ("Deleted Customer with id - " + id);
	}
}
