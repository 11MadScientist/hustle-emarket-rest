package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;

public interface SellerService
{
	public List<Seller> getSeller();

	public List<Seller> getSeller(GetRequestUser getRequest);

	public Seller getSellerById(int id);

	public Seller addSeller(Seller seller);

	public Seller updateSeller(Seller seller);

	public void deleteSeller(Seller seller);

	public void deleteSellerById(int id);

	public Seller findSellerByUsername(String username);

	public int countSellerRequest();

	Seller loginSeller(Seller seller);

	Seller logoutSeller(Seller seller);

	Seller changePass(PutRequestChangePassword request);

}
