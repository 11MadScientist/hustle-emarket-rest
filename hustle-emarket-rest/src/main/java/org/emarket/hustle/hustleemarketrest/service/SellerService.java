package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Seller;

public interface SellerService
{
	public List<Seller> getSeller();

	public Seller getSellerById(int id);

	public void saveSeller(Seller seller);

	public void deleteSeller(Seller seller);

	public void deleteSellerById(int id);

	public Seller loginSeller(String username);
}
