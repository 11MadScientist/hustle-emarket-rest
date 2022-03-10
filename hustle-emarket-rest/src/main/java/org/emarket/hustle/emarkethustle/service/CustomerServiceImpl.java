package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.CustomerRepository;
import org.emarket.hustle.emarkethustle.entity.Customer;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService
{
	Logger log = Logger.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getCustomer()
	{
		List<Customer> customers = customerRepository.findAll();

		if(customers.isEmpty())
		{
			throw new NotFoundException("CUSTOMERS");
		}

		return customers;
	}

	@Override
	public List<Customer> getCustomer(GetRequestUser getRequest)
	{
		// @formatter:off
		Pageable pageable = PageRequest.of(getRequest.getPage(),
							getRequest.getSize(),
							Sort.by(Sort.Direction.ASC, getRequest.getField()));


		Slice<Customer> slicedCustomers = null;

		// @formatter:off
		if(getRequest.getSearchField().equals("name"))
		{
			slicedCustomers = customerRepository.findCustomerByFields(
					getRequest.isProhibited(),
					getRequest.getSearchPattern(),
					pageable);
		}

		else if(getRequest.getSearchField().equals("username"))
		{
			slicedCustomers = customerRepository.findCustomerByUsernameLike
					("%"+getRequest.getSearchPattern()+"%", pageable);
		}




		List<Customer> customers = slicedCustomers.getContent();

		if(customers.isEmpty())
		{
			throw new NotFoundException("CUSTOMERS");
		}

		return customers;
	}

	@Override
	public Customer getCustomerById(int id)
	{
		Optional<Customer> result = customerRepository.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("CUSTOMER WITH ID: " + id);
		}

		return result.get();
	}

	@Override
	public Customer saveCustomer(Customer customer)
	{
		try
		{
			return customerRepository.save(customer);

		}
		catch (Exception e)
		{
			throw new FailedException("SAVING CUSTOMER");
		}

	}

	@Override
	public void deleteCustomerById(int id)
	{
		try
		{
			customerRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETE CUSTOMER BY ID");
		}
	}

	@Override
	public Customer findCustomerByUsername(String username)
	{
		try
		{
			return customerRepository.findCustomerByUsername(username);
		}
		catch (Exception e)
		{
			throw new NotFoundException("CUSTOMER WITH USERNAME" + username);
		}

	}

}
