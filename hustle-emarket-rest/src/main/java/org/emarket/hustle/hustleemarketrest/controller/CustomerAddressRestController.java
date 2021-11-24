package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
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
		return customerAddressService.getCustomerAddress();
	}

	@GetMapping("/customer-address/{id}")
	public CustomerAddress getCustomerAddressById(@PathVariable int id)
	{
		return customerAddressService.getCustomerAddressById(id);
	}

//	@PostMapping("/customer-address")
//	public CustomerAddress addCustomerAddress(@RequestBody CustomerAddress customerAddress)
//	{
//		customerAddress.setId(0);
//		customerAddressService.saveCustomerAddress(customerAddress);
//		return customerAddress;
//	}

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
