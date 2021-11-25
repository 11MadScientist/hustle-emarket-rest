package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerDetail;
import org.emarket.hustle.hustleemarketrest.error.CustomerNotFoundException;
import org.emarket.hustle.hustleemarketrest.error.UniqueErrorException;
import org.emarket.hustle.hustleemarketrest.service.CustomerDetailService;
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
public class CustomerDetailRestController
{
	@Autowired
	CustomerDetailService customerDetailService;

	@GetMapping("/customer-details")
	public List<CustomerDetail> getCustomerDetail()
	{
		// check if customer is retrieved
		try
		{
			return customerDetailService.getCustomerDetail();
		}
		catch (Exception e)
		{
			throw new CustomerNotFoundException("No Customer Detail/s Found");
		}

	}

	@GetMapping("/customer-details/{id}")
	public CustomerDetail getCustomerDetailById(@PathVariable int id)
	{
		try
		{
			return customerDetailService.getCustomerDetailById(id);
		}
		catch (Exception e)
		{
			throw new CustomerNotFoundException("Customer Details with id:" + id + " was not found");
		}

	}

	// @PostMapping is fobidden in CustomerDetail because it needs to be Saved with
	// Customer
	/*
	 * @PostMapping("/customer-details")
	 * public CustomerDetail addCustomerDetail(@RequestBody CustomerDetail
	 * customerDetail)
	 * {
	 * customerDetail.setId(0);
	 * customerDetailService.saveCustomerDetail(customerDetail);
	 * return customerDetail;
	 * }
	 */

	@PutMapping("/customer-details")
	public CustomerDetail updateCustomerDetail(@RequestBody CustomerDetail customerDetail)
	{
		try
		{
			customerDetailService.saveCustomerDetail(customerDetail);
			return customerDetail;
		}
		catch (Exception e)
		{
			throw new UniqueErrorException("Customer [email, username] should be unique");
		}

	}

	@DeleteMapping("/customer-details/{id}")
	public String deleteCustomerDetail(@PathVariable int id)
	{

		CustomerDetail customerDetail = customerDetailService.getCustomerDetailById(id);

		if(customerDetail == null)
		{
			throw new CustomerNotFoundException("Customer Details with id:" + id + " was not found");
		}

		customerDetail.getCustomer().setCustomerDetail(null);

		customerDetailService.deleteCustomerDetail(customerDetail);

		return ("Deleted Customer Detail with id - " + id);
	}

}
