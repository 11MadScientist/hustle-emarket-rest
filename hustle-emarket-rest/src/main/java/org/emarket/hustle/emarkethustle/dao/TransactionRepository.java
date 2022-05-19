package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>
{
	public List<Transaction> findByCreationDateLikeOrderByIdDesc(String value);

	public List<Transaction> findByCustomerIdOrderByIdDesc(
			int id);

	public List<Transaction> findByRiderIdOrderByIdDesc(
			int id);

	public Transaction findByRiderIdAndStatus(int id, String status);

	@Query("SELECT i FROM Transaction i WHERE i.customer.id =:id"
			+ " AND i.status = 'Pending' OR i.status = 'Preparing'")
	public List<Transaction> findByCustomerPreparingAndPending(@Param("id") int id);

//	public List<Transaction>
//			findByCustomerIdAndByStatusEqualsPreparingOrByStatusEquals(int id, String preparing, String pending);
}
