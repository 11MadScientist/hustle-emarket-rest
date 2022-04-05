package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
	public Slice<Transaction> findByCustomerId(
			int id, Pageable pageable);

	public Slice<Transaction> findByRiderId(
			int id, Pageable pageable);

	public Transaction findByRiderIdAndStatus(int id, String status);
}
