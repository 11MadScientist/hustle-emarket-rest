package org.emarket.hustle.hustleemarketrest.dao;

import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	public Customer findCustomerByUsername(String username);
}
