package org.emarket.hustle.emarkethustle.dao;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer>
{
	List<CustomerAddress> findCustomerAddressByCustomerId(int id);
}
