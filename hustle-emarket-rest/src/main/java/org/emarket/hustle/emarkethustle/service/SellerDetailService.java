package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.SellerDetail;

public interface SellerDetailService
{
	public List<SellerDetail> getSellerDetail();

	public SellerDetail getSellerDetailById(int id);

	public void saveSellerDetail(SellerDetail sellerDetail);

	public void deleteSellerDetailById(int id);

	public void deleteSellerDetail(SellerDetail sellerDetail);
}
