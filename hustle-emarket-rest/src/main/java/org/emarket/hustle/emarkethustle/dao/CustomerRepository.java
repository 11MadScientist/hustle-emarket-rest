package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	public Customer findCustomerByUsername(String username);

	public Slice<Customer> findCustomerByFirstNameLikeOrLastNameLike(
			String searchPattern, String searchPattern2, Pageable pageable);

	public Slice<Customer> findCustomerByUsernameLike(
			String searchPattern, Pageable pageable);

	@Query("SELECT i FROM Customer i WHERE i.customerDetail.prohibited =:prohibited "
			+ "and (i.firstName LIKE %:searchPattern% or i.lastName LIKE %:searchPattern% "
			+ "or i.username LIKE %:searchPattern%)")
	public Slice<Customer> findCustomerByFields(
			@Param("prohibited") boolean prohibited,
			@Param("searchPattern") String searchPattern,
			Pageable pageable);

}
