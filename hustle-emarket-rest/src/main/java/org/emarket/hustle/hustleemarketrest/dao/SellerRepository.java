package org.emarket.hustle.hustleemarketrest.dao;

import org.emarket.hustle.hustleemarketrest.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer>
{

	Seller findSellerByUsername(String username);

}
