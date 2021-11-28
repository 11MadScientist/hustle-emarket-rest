package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.SellerRepository;
import org.emarket.hustle.hustleemarketrest.entity.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService
{

	@Autowired
	SellerRepository sellerRepository;

	@Override
	public List<Seller> getSeller()
	{
		return sellerRepository.findAll();
	}

	@Override
	public Seller getSellerById(int id)
	{
		Optional<Seller> seller = sellerRepository.findById(id);

		if(seller.isEmpty())
		{
			throw new RuntimeException("Seller with id:" + id + "was not found");
		}

		return seller.get();
	}

	@Override
	public void saveSeller(Seller seller)
	{
		sellerRepository.save(seller);
	}

	@Override
	public void deleteSeller(Seller seller)
	{
		sellerRepository.delete(seller);

	}

	@Override
	public void deleteSellerById(int id)
	{
		sellerRepository.deleteById(id);

	}

	@Override
	public Seller loginSeller(String username)
	{
		Seller seller = sellerRepository.findSellerByUsername(username);

		return seller;

	}

}
