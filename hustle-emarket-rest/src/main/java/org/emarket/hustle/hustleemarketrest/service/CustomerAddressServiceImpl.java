package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.CustomerAddressRepository;
import org.emarket.hustle.hustleemarketrest.entity.CustomerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService
{

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	@Override
	public List<CustomerAddress> getCustomerAddress()
	{
		return customerAddressRepository.findAll();
	}

	@Override
	public CustomerAddress getCustomerAddressById(int id)
	{
		Optional<CustomerAddress> result = customerAddressRepository.findById(id);

		if(result.isEmpty())
		{
			throw new RuntimeException("Not Found: CustomerAddress with id: " + id);
		}

		return result.get();
	}

	@Override
	public void saveCustomerAddress(CustomerAddress customerDetail)
	{
		customerAddressRepository.save(customerDetail);

	}

	@Override
	public void deleteCustomerAddressById(int id)
	{
		customerAddressRepository.deleteById(id);

	}

}
