package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.SellerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerDetailRepository extends JpaRepository<SellerDetail, Integer>
{
	public SellerDetail findByEmail(String email);

	public SellerDetail findByPhoneNumber(String phoneNumber);
}
