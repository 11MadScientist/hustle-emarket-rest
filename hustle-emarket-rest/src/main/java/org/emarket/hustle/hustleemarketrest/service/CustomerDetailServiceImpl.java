package org.emarket.hustle.hustleemarketrest.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.hustleemarketrest.dao.CustomerDetailRepository;
import org.emarket.hustle.hustleemarketrest.entity.CustomerDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService
{

	@Autowired
	private CustomerDetailRepository customerDetailRepository;

	@Override
	public List<CustomerDetail> getCustomerDetail()
	{
		return customerDetailRepository.findAll();
	}

	@Override
	public CustomerDetail getCustomerDetailById(int id)
	{
		Optional<CustomerDetail> result = customerDetailRepository.findById(id);

		if(result.isEmpty())
		{
			throw new RuntimeException("Did not find CustomerDetail id - " + id);
		}
		return result.get();

	}

	@Override
	public void saveCustomerDetail(CustomerDetail customerDetail)
	{
		customerDetailRepository.save(customerDetail);
	}

	@Override
	public void deleteCustomerDetailById(int id)
	{
		// TODO Auto-generated method stub

	}

}
