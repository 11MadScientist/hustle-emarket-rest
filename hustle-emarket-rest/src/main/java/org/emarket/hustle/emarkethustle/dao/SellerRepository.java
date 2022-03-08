package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SellerRepository extends JpaRepository<Seller, Integer>
{

	Seller findSellerByUsername(String username);

	public Slice<Seller> findSellerByFirstNameLikeOrLastNameLike(
			String searchPattern, String searchPattern2, Pageable pageable);

	public Slice<Seller> findSellerByUsernameLike(
			String searchPattern, Pageable pageable);

	@Query("SELECT i FROM Seller i WHERE i.sellerDetail.authorized =:authorized and i.sellerDetail.prohibited =:prohibited "
			+ "and (i.firstName LIKE %:searchPattern% or i.lastName LIKE %:searchPattern% "
			+ "or i.username LIKE %:searchPattern%)")
	public Slice<Seller> findSellerByFields(
			@Param("authorized") boolean authorize,
			@Param("prohibited") boolean prohibited,
			@Param("searchPattern") String searchPattern,
			Pageable pageable);

	@Query("SELECT COUNT(i) FROM Seller i WHERE i.sellerDetail.authorized = false")
	public int countSellerRequest();

}
