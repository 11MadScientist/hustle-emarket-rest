package org.emarket.hustle.hustleemarketrest.dao;

import org.emarket.hustle.hustleemarketrest.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Integer>
{
	@Modifying
	@Query("update Store s set s.storeName =:storeName, s.storeAddress =:storeAddress,"
			+ "s.overallRating =:overallRating, s.itemsAdded =:itemsAdded where s.id =:id")
	public void updateStore(
			@Param("storeName") String storeName, @Param("storeAddress") String storeAddress,
			@Param("overallRating") double overallRating, @Param("itemsAdded") int itemsAdded,
			@Param("id") int id);

}
