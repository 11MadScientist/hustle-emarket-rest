package org.emarket.hustle.hustleemarketrest.dao;

import org.emarket.hustle.hustleemarketrest.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Integer>
{
	@Modifying
	@Query("DELETE from Item b WHERE b.store.id=:id")
	public void deleteItemByStoreId(@Param("id") int id);
}
