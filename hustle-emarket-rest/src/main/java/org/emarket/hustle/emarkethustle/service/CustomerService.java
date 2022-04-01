package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Customer;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;

public interface CustomerService
{
	public List<Customer> getCustomer();

	public List<Customer> getCustomer(GetRequestUser getRequest);

	public Customer getCustomerById(int id);

	Customer addCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	public void deleteCustomerById(int id);

	Customer findCustomerByUsername(String username);

	Customer loginCustomer(Customer customer);

	Customer logoutCustomer(Customer customer);

	Customer changePass(PutRequestChangePassword request);

}
