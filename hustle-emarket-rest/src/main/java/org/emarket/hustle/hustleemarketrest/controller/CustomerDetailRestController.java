package org.emarket.hustle.hustleemarketrest.controller;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.SellerDetail;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.emarket.hustle.hustleemarketrest.response.ProcessConfirmation;
import org.emarket.hustle.hustleemarketrest.response.UniqueErrorException;
import org.emarket.hustle.hustleemarketrest.service.SellerDetailService;
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
 * there is no post/insert in SellerDetail
 * because you need to be a Seller to create
 * a SellerDetail
 *
 */
@RestController
@RequestMapping("/emarket-hustle")
public class CustomerDetailRestController
{
	@Autowired
	SellerDetailService sellerDetailService;

	/*
	 * #######################################
	 * ########## GET SELLER DETAIL ##########
	 * #######################################
	 */
	@GetMapping("/seller-details")
	public List<SellerDetail> getSellerDetail()
	{
		// check if seller is retrieved
		try
		{
			return sellerDetailService.getSellerDetail();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new NotFoundException("NO SELLER DETAIL");
		}

	}

	/*
	 * #######################################
	 * ###### GET SELLER DETAIL BY ID ########
	 * #######################################
	 */

	@GetMapping("/seller-details/{id}")
	public SellerDetail getSellerDetailById(@PathVariable int id)
	{
		try
		{
			return sellerDetailService.getSellerDetailById(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new NotFoundException("SELLER DETAIL WITH ID: " + id);
		}

	}

	/*
	 * #######################################
	 * ######## UPDATE SELLER DETAIL #########
	 * #######################################
	 */

	@PutMapping("/seller-details")
	public SellerDetail updateSellerDetail(@RequestBody SellerDetail sellerDetail)
	{
		try
		{
			sellerDetailService.saveSellerDetail(sellerDetail);
			return sellerDetail;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new UniqueErrorException("SELLER [EMAIL]");
		}

	}

	/*
	 * #######################################
	 * ####### DELETE SELLER DETAIL ##########
	 * #######################################
	 */

	@DeleteMapping("/seller-details/{id}")
	public ProcessConfirmation deleteSellerDetail(@PathVariable int id)
	{

		try
		{
			SellerDetail sellerDetail = sellerDetailService.getSellerDetailById(id);

			if(sellerDetail == null)
			{
				throw new NotFoundException("SELLER WITH ID: " + id);
			}

			/*
			 * severe the ties between the customer and the customerdetail
			 * so we can safely delete the customerdetail
			 */
			sellerDetail.getSeller().setSellerDetail(null);

			sellerDetailService.deleteSellerDetail(sellerDetail);

			return new ProcessConfirmation("SUCCESS",
					"SELLERDETAIL", "SELLER DETAIL WITH ID: " + id + " WAS DELETED.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
