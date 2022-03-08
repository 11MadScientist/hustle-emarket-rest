package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.dao.SellerRepository;
import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService
{

	Logger log = Logger.getLogger(SellerServiceImpl.class.getName());

	@Autowired
	SellerRepository sellerRepository;

	@Override
	public List<Seller> getSeller()
	{
		List<Seller> sellers = sellerRepository.findAll();

		if(sellers.isEmpty())
		{
			throw new NotFoundException("SELLERS");
		}

		return sellers;
	}

	@Override
	public List<Seller> getSeller(GetRequestUser getRequest)
	{

		// @formatter:off
		Pageable pageable = PageRequest.of(getRequest.getPage(),
							getRequest.getSize(),
							Sort.by(Sort.Direction.ASC, getRequest.getField()));


		Slice<Seller> slicedSellers = null;

		// @formatter:off
		if(getRequest.getSearchField().equals("name"))
		{
			slicedSellers = sellerRepository.findSellerByFirstNameLikeOrLastNameLike
			("%"+getRequest.getSearchPattern()+"%","%"+getRequest.getSearchPattern()+"%"
							, pageable);
		}

		else if(getRequest.getSearchField().equals("username"))
		{
			slicedSellers = sellerRepository.findSellerByUsernameLike
					("%"+getRequest.getSearchPattern()+"%", pageable);
		}

		else if(getRequest.getSearchField().equals("authorized"))
		{
			slicedSellers = sellerRepository.findSellerByFields(
					getRequest.isAuthorized(),
					getRequest.isProhibited(),
					getRequest.getSearchPattern(),
					pageable);

		}

		List<Seller> sellers = null;
		if(!slicedSellers.isEmpty())
		{
			 sellers = slicedSellers.getContent();

		}



		return sellers;
	}

	@Override
	public Seller getSellerById(int id)
	{
		Optional<Seller> seller = sellerRepository.findById(id);

		if(seller.isEmpty())
		{
			throw new NotFoundException("SELLER WITH ID: " + id);
		}

		return seller.get();
	}

	@Override
	public Seller saveSeller(Seller seller)
	{
		try
		{
			return sellerRepository.save(seller);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING SELLER");
		}
	}

	@Override
	public void deleteSeller(Seller seller)
	{
		try
		{
			sellerRepository.delete(seller);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETE SELLER");
		}

	}

	@Override
	public void deleteSellerById(int id)
	{
		try
		{
			sellerRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETE SELLER WITH ID: " + id);
		}

	}

	@Override
	public Seller loginSeller(String username)
	{
		try
		{
			return sellerRepository.findSellerByUsername(username);
		}
		catch (Exception e)
		{
			throw new NotFoundException("SELLER WITH USERNAME: " + username);
		}
	}

	@Override
	public int countSellerRequest()
	{
		return sellerRepository.countSellerRequest();
	}



}
