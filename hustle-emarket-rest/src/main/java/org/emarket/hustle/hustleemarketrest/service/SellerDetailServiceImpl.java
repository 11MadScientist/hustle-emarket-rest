package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.SellerDetailRepository;
import org.emarket.hustle.hustleemarketrest.entity.SellerDetail;
import org.emarket.hustle.hustleemarketrest.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerDetailServiceImpl implements SellerDetailService
{

	@Autowired
	private SellerDetailRepository sellerDetailService;

	@Override
	public List<SellerDetail> getSellerDetail()
	{
		return sellerDetailService.findAll();
	}

	@Override
	public SellerDetail getSellerDetailById(int id)
	{
		Optional<SellerDetail> result = sellerDetailService.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("SELLERDETAIL WITH ID: " + id);
		}
		return result.get();

	}

	@Override
	public void saveSellerDetail(SellerDetail sellerDetail)
	{
		sellerDetailService.save(sellerDetail);
	}

	@Override
	public void deleteSellerDetailById(int id)
	{
		sellerDetailService.deleteById(id);

	}

	@Override
	public void deleteSellerDetail(SellerDetail sellerDetail)
	{
		sellerDetailService.delete(sellerDetail);

	}

}
