package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;

public interface SellerService
{
	public List<Seller> getSeller();

	public List<Seller> getSeller(GetRequestUser getRequest);

	public Seller getSellerById(int id);

	public Seller saveSeller(Seller seller);

	public void deleteSeller(Seller seller);

	public void deleteSellerById(int id);

	public Seller findSellerByUsername(String username);

	public int countSellerRequest();
}
