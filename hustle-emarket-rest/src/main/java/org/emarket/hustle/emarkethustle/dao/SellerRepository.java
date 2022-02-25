package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer>
{

	Seller findByUsername(String username);

	public Slice<Seller> findByFirstNameLikeOrLastNameLike(
			String searchPattern, String searchPattern2, Pageable pageable);

	public Slice<Seller> findByUsernameLike(
			String searchPattern, Pageable pageable);

}
