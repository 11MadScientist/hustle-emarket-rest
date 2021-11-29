package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.CustomerDetail;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.response.UniqueErrorException;
import org.emarket.hustle.hustleemarketrest.service.CustomerDetailService;
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
 * there is no post/insert in CustomerDetail
 * because you need to be a Customer to create
 * a CustomerDetail
 *
 */
@RestController
@RequestMapping("/emarket-hustle")
public class SellerDetailController
{
	@Autowired
	CustomerDetailService customerDetailService;

	/*
	 * #######################################
	 * ######## GET CUSTOMER DETAIL ##########
	 * #######################################
	 */
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
			e.printStackTrace();
			throw new NotFoundException("NO CUSTOMER DETAIL");
		}

	}

	/*
	 * #######################################
	 * ##### GET CUSTOMER DETAIL BY ID #######
	 * #######################################
	 */

	@GetMapping("/customer-details/{id}")
	public CustomerDetail getCustomerDetailById(@PathVariable int id)
	{
		try
		{
			return customerDetailService.getCustomerDetailById(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new NotFoundException("CUSTOMER DETAIL WITH ID: " + id);
		}

	}

	/*
	 * #######################################
	 * ####### UPDATE CUSTOMER DETAIL ########
	 * #######################################
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
			e.printStackTrace();
			throw new UniqueErrorException("CUSTOMER [EMAIL]");
		}

	}

	/*
	 * #######################################
	 * ###### DELETE CUSTOMER DETAIL #########
	 * #######################################
	 */

	@DeleteMapping("/customer-details/{id}")
	public ProcessConfirmation deleteCustomerDetail(@PathVariable int id)
	{

		try
		{
			CustomerDetail customerDetail = customerDetailService.getCustomerDetailById(id);

			if(customerDetail == null)
			{
				throw new NotFoundException("CUSTOMER WITH ID: " + id);
			}

			/*
			 * severe the ties between the customer and the customerdetail
			 * so we can safely delete the customerdetail
			 */
			customerDetail.getCustomer().setCustomerDetail(null);

			customerDetailService.deleteCustomerDetail(customerDetail);

			return new ProcessConfirmation("SUCCESS",
					"CUSTOMERDETAIL", "CUSTOMER DETAIL WITH ID: " + id + " WAS DELETED.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
