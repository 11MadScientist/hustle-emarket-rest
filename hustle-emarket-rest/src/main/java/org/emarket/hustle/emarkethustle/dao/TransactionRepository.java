package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
	public Slice<Transaction> findByCustomer(
			int id, Pageable pageable);

	public Slice<Transaction> findByRider(
			int id, Pageable pageable);
}
