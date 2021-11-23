package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.CustomerRepository;
import org.emarket.hustle.hustleemarketrest.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getCustomer()
	{
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(int id)
	{
		Optional<Customer> result = customerRepository.findById(id);

		if(result.isEmpty())
		{
			throw new RuntimeException("Did not find employee id - " + id);
		}

		return result.get();
	}

	@Override
	public void saveCustomer(Customer customer)
	{
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerById(int id)
	{
		customerRepository.deleteById(id);
	}

}
