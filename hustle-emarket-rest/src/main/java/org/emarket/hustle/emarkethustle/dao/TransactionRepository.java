package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
	public List<Transaction> findByCustomerIdOrderByIdDesc(
			int id);

	public List<Transaction> findByRiderIdOrderByIdDesc(
			int id);

	public Transaction findByRiderIdAndStatus(int id, String status);
}
