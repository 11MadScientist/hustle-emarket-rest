package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
import org.emarket.hustle.hustleemarketrest.error.CustomerNotFoundException;
import org.emarket.hustle.hustleemarketrest.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emarket-hustle")
public class CustomerAddressRestController
{
	@Autowired
	private CustomerAddressService customerAddressService;

	@GetMapping("/customer-address")
	public List<CustomerAddress> getCustomerAddress()
	{
		try
		{
			return customerAddressService.getCustomerAddress();
		}
		catch (Exception e)
		{
			throw new CustomerNotFoundException("No Customer Address/es Found");
		}

	}

	@GetMapping("/customer-address/{id}")
	public CustomerAddress getCustomerAddressById(@PathVariable int id)
	{
		try
		{
			return customerAddressService.getCustomerAddressById(id);
		}
		catch (Exception e)
		{
			throw new CustomerNotFoundException("Customer Address with id:" + id + " was not found");
		}

	}

	// @PostMapping is forbidden in CustomerAddress because it needs to be Saved
	// with
	// Customer

	/*
	 * @PostMapping("/customer-address")
	 * public CustomerAddress addCustomerAddress(@RequestBody CustomerAddress
	 * customerAddress)
	 * {
	 * customerAddress.setId(0);
	 * customerAddressService.saveCustomerAddress(customerAddress);
	 * return customerAddress;
	 * }
	 */

	@PutMapping("/customer-address")
	public CustomerAddress updateCustomerAddress(@RequestBody CustomerAddress customerAddress)
	{
		customerAddressService.saveCustomerAddress(customerAddress);
		return customerAddress;
	}

	@DeleteMapping("/customer-address/{id}")
	public String deleteCustomerAddress(@PathVariable int id)
	{
		customerAddressService.deleteCustomerAddressById(id);
		return ("Deleted Customer Address with id - " + id);
	}

}
