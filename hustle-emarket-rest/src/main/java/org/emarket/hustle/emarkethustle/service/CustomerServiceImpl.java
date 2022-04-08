package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.CustomerRepository;
import org.emarket.hustle.emarkethustle.entity.Customer;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.EmailMessages;
import org.emarket.hustle.emarkethustle.response.ErrorLoginException;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.UniqueErrorException;
import org.emarket.hustle.emarkethustle.security.BcryptSecurity;
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

	@Autowired
	private EmailSenderService emailSender;

//	for duplicate validation
	@Autowired
	private ValidationService validationService;

	// for the bean bCrypt
	@Autowired
	private BcryptSecurity bcrypt;

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
	public Customer addCustomer(Customer customer)
	{
		// check if the username is already taken and if the email is taken

		if(!validationService.isEmailNotTaken(customer.getCustomerDetail().getEmail()))
		{
			throw new UniqueErrorException("Email ");
		}

		if(!validationService.isUsernameNotTaken(customer.getUsername()))
		{
			throw new UniqueErrorException("Username ");
		}

		/*
		 * set customer id to 0 to trigger INSERT, not UPDATE
		 * check if the customerDetail exist
		 * change id to 0 if exists
		 */
		customer.setId(0);
//				customer.setCreationDate(customer.getModifiedDate());

		/* encrypting password using bcrypt */
		customer.setPassword(bcrypt.encode(customer.getPassword()));

		customerRepository.save(customer);

		emailSender.sendEmail(customer.getCustomerDetail().getEmail(),
				"Emarket Customer Registration",
				EmailMessages.registrationMessage("RIDER"));

		return customer;


	}

	@Override
	public Customer updateCustomer(Customer customer)
	{
		try
		{
			/*
			 * because we do not pass the password to the client, we need to update
			 * the password when the client wants to update their data.
			 * we can do this by getting the data first then pinning the saved password
			 * from the database to passed Customer data
			 */

			Customer dbCustomer = getCustomerById(customer.getId());

			if(dbCustomer == null)
			{
				throw new NotFoundException("CUSTOMER WITH ID: " + customer.getId());
			}
			customer.setPassword(dbCustomer.getPassword());
			customer.setCustomerAddress(dbCustomer.getCustomerAddress());
			customer.setBasket(dbCustomer.getBasket());
			log.info(customer);
			log.info(dbCustomer);
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

	@Override
	public Customer loginCustomer(Customer customer)
	{
		Customer dbCustomer = findCustomerByUsername(customer.getUsername());

		if(bcrypt.matches(customer.getPassword(), dbCustomer.getPassword()))
		{
			if(!dbCustomer.getCustomerDetail().isProhibited())
			{
				dbCustomer.getCustomerDetail().setStatus(true);
				customerRepository.save(dbCustomer);
				return dbCustomer;
			}
		}
		throw new ErrorLoginException("CUSTOMER [Username, Password]");
	}

	@Override
	public Customer logoutCustomer(Customer customer)
	{
		Customer dbCustomer = findCustomerByUsername(customer.getUsername());

		dbCustomer.getCustomerDetail().setStatus(false);
		customerRepository.save(dbCustomer);
		return dbCustomer;
	}

	@Override
	public Customer changePass(PutRequestChangePassword changePass)
	{

		Customer dbCustomer = getCustomerById(changePass.getId());

		if(bcrypt.matches(changePass.getPassword(), dbCustomer.getPassword()))
		{
			dbCustomer.setPassword(bcrypt.encode(changePass.getNewPassword()));

			customerRepository.save(dbCustomer);
			return dbCustomer;
		}

		throw new FailedException("UPDATING PASSWORD");
	}


}
