package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerDetail;
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

		return customerDetailService.getCustomerDetail();
	}

	@GetMapping("/customer-details/{id}")
	public CustomerDetail getCustomerDetailById(@PathVariable int id)
	{
		return customerDetailService.getCustomerDetailById(id);

	}

//	@PostMapping("/customer-details")
//	public CustomerDetail addCustomerDetail(@RequestBody CustomerDetail customerDetail)
//	{
//		customerDetail.setId(0);
//		customerDetailService.saveCustomerDetail(customerDetail);
//		return customerDetail;
//	}

	@PutMapping("/customer-details")
	public CustomerDetail updateCustomerDetail(@RequestBody CustomerDetail customerDetail)
	{
		customerDetailService.saveCustomerDetail(customerDetail);
		return customerDetail;
	}

	@DeleteMapping("/customer-details/{id}")
	public String deleteCustomerDetail(@PathVariable int id)
	{

		CustomerDetail customerDetail = customerDetailService.getCustomerDetailById(id);

		customerDetail.getCustomer().setCustomerDetail(null);

		customerDetailService.deleteCustomerDetail(customerDetail);

		return ("Deleted Customer Detail with id - " + id);
	}

}
