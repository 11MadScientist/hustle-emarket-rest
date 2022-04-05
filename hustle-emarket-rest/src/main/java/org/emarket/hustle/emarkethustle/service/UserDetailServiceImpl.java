package org.emarket.hustle.emarkethustle.service;

import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.dao.AdminRepository;
import org.emarket.hustle.emarkethustle.dao.CustomerRepository;
import org.emarket.hustle.emarkethustle.dao.RiderRepository;
import org.emarket.hustle.emarkethustle.dao.SellerRepository;
import org.emarket.hustle.emarkethustle.entity.User;
import org.emarket.hustle.emarkethustle.entity.UserDetailsImpl;
import org.emarket.hustle.emarkethustle.restcontroller.BasketRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService
{
	Logger log = Logger.getLogger(BasketRestController.class.getName());
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	RiderRepository riderRepository;

	@Autowired
	AdminRepository adminRepository;

	private User user = null;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		user = null;

		user = customerRepository.findCustomerByUsername(username);
		if(user == null)
		{
			user = sellerRepository.findSellerByUsername(username);

			if(user == null)
			{

				user = riderRepository.findByUsername(username);

				if(user == null)
				{
					user = adminRepository.findAdminByUsername(username);

					if(user == null)
					{
						log.info("user is null");
						throw new UsernameNotFoundException("Not found: " + username);
					}
				}
			}
		}
//		log.info(user + "");
		return new UserDetailsImpl(user);
	}

}
