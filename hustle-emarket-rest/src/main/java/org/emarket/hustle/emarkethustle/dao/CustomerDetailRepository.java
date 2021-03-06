package org.emarket.hustle.emarkethustle.dao;

import org.emarket.hustle.emarkethustle.entity.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Integer>
{
	public CustomerDetail findByEmail(String email);

	public CustomerDetail findByPhoneNumber(String phoneNumber);
}
