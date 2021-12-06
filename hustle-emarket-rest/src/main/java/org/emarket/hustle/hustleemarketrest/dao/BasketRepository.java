package org.emarket.hustle.hustleemarketrest.dao;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepository extends JpaRepository<Basket, Integer>
{

	public List<Basket> findByCustomerId(int customerId);

	@Modifying
	@Query("DELETE FROM Basket b WHERE b.customer.getId()=:customerId")
	public void deleteCustomerBasket(@Param("customerId") int customerId);
}
