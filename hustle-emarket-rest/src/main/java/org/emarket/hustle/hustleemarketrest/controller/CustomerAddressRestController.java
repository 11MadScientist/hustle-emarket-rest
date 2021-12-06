package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * there is no post/insert in CustomerAddress
 * because you need to be a Customer to create
 * a CustomerAddress
 *
 */

@RestController
@RequestMapping("${api.basePath}")
public class CustomerAddressRestController
{
	@Autowired
	private CustomerAddressService customerAddressService;

	/*
	 * #######################################
	 * ######## GET CUSTOMER ADDRESS #########
	 * #######################################
	 */
	@GetMapping("/customer-addresses")
	public List<CustomerAddress> getCustomerAddress()
	{
		return customerAddressService.getCustomerAddress();
	}

	/*
	 * #######################################
	 * ##### GET CUSTOMER ADDRESS BY ID ######
	 * #######################################
	 */
	@GetMapping("/customer-addresses/{id}")
	public CustomerAddress getCustomerAddressById(@PathVariable int id)
	{
		return customerAddressService.getCustomerAddressById(id);
	}

	/*
	 * #######################################
	 * ###### UPDATE CUSTOMER ADDRESS ########
	 * #######################################
	 */
	@PutMapping("/customer-addresses")
	public CustomerAddress updateCustomerAddress(@RequestBody CustomerAddress customerAddress)
	{
		customerAddressService.saveCustomerAddress(customerAddress);
		return customerAddress;
	}

	/*
	 * #######################################
	 * ####### DELETE CUSTOMER ADDRESS #######
	 * #######################################
	 */
	@DeleteMapping("/customer-addresses/{id}")
	public ProcessConfirmation deleteCustomerAddress(@PathVariable int id)
	{
		customerAddressService.deleteCustomerAddressById(id);

		return new ProcessConfirmation("SUCCESS", "CUSTOMERDETAIL",
				"CUSTOMER ADDRESS WITH ID: " + id + " WAS DELETED.");
	}

}
