package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>
{
	public Slice<Store> findStoreByStoreNameLike(String searchPattern, Pageable pageable);

	public Slice<Store> findStoreByStoreAddressLike(String searchPattern, Pageable pageable);
}
