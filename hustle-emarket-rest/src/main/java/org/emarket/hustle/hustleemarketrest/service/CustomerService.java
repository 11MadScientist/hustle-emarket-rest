package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;

import org.emarket.hustle.hustleemarketrest.entity.Customer;

public interface CustomerService
{
	public List<Customer> getCustomer();

	public Customer getCustomerById(int id);

	public void saveCustomer(Customer customer);

	public void deleteCustomerById(int id);

}
