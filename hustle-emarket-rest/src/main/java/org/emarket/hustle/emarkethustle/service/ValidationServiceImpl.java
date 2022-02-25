package org.emarket.hustle.emarkethustle.service;

import org.emarket.hustle.emarkethustle.dao.CustomerDetailRepository;
import org.emarket.hustle.emarkethustle.dao.CustomerRepository;
import org.emarket.hustle.emarkethustle.dao.SellerDetailRepository;
import org.emarket.hustle.emarkethustle.dao.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService
{
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerDetailRepository customerDetailRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	SellerDetailRepository sellerDetailRepository;

	@Override
	public boolean isEmailNotTaken(String email)
	{
		if(customerDetailRepository.findByEmail(email) == null
				&& sellerDetailRepository.findByEmail(email) == null)
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean isUsernameNotTaken(String username)
	{
		if(customerRepository.findCustomerByUsername(username) == null
				&& sellerRepository.findSellerByUsername(username) == null)
		{
			return true;
		}

		return false;
	}

}
