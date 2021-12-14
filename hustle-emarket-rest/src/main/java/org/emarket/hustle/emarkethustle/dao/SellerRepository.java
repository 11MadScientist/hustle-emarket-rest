package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer>
{

	Seller findSellerByUsername(String username);

	public Slice<Seller> findSellerByFirstNameLikeOrLastNameLike(
			String searchPattern, String searchPattern2, Pageable pageable);

	public Slice<Seller> findSellerByUsernameLike(
			String searchPattern, Pageable pageable);

}
