package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer>
{
	public List<History> findHistoryByStoreIdOrderByIdDesc(int id);

	public Slice<History> findHistoryByItemId(int id, Pageable pageable);

}
