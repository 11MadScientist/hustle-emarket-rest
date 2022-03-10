package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Customer;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;

public interface CustomerService
{
	public List<Customer> getCustomer();

	public List<Customer> getCustomer(GetRequestUser getRequest);

	public Customer getCustomerById(int id);

	public Customer saveCustomer(Customer customer);

	public void deleteCustomerById(int id);

	Customer findCustomerByUsername(String username);

}
