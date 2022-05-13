package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Item;
import org.emarket.hustle.emarkethustle.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Integer>
{
	public List<Item> findByDelisted(boolean delisted);

	@Query("SELECT i FROM Item i WHERE i.name LIKE:name AND i.category IN :categories AND delisted=false")
	public List<Item> findByCategory(
			@Param("categories") List<Integer> categories,
			@Param("name") String name);

	@Modifying
	@Query("DELETE from Item b WHERE b.store.id=:id")
	public void deleteItemByStoreId(@Param("id") int id);

	public List<Item> findByStore(Store store);
}
