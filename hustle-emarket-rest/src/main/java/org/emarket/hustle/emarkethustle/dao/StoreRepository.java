package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Integer>
{
	public Slice<Store> findStoreByStoreNameLike(String searchPattern, Pageable pageable);

	public Slice<Store> findStoreByStoreAddressLike(String searchPattern, Pageable pageable);

	@Query("SELECT i FROM Store i WHERE i.seller.sellerDetail.authorized =:authorized "
			+ "and i.seller.sellerDetail.prohibited =:prohibited "
			+ "and i.storeName LIKE %:searchPattern% "
			+ "and i.storeAddress LIKE %:storeAddress%")
	public List<Store> findStoreByFields(
			@Param("authorized") boolean authorize,
			@Param("prohibited") boolean prohibited,
			@Param("storeAddress") String storeAddress,
			@Param("searchPattern") String searchPattern);

	@Query("SELECT COUNT(i) FROM Store i WHERE i.seller.sellerDetail.authorized =true and "
			+ "i.seller.sellerDetail.prohibited=false")
	public int countStores();

}
