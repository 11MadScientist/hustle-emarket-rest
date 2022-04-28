package org.emarket.hustle.emarkethustle.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javax.mail.MessagingException;

import org.emarket.hustle.emarkethustle.dao.SellerRepository;
import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.EmailMessages;
import org.emarket.hustle.emarkethustle.response.ErrorLoginException;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.UniqueErrorException;
import org.emarket.hustle.emarkethustle.security.BcryptSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;

@Service
public class SellerServiceImpl implements SellerService
{

	Logger log = Logger.getLogger(SellerServiceImpl.class.getName());

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private BcryptSecurity bcrypt;

	@Autowired
	private EmailSenderService emailSender;

	@Override
	public List<Seller> getSeller()
	{
		List<Seller> sellers = sellerRepository.findAll();

		if(sellers.isEmpty())
		{
			throw new NotFoundException("SELLERS");
		}

		return sellers;
	}

	@Override
	public List<Seller> getSeller(GetRequestUser getRequest)
	{

		// @formatter:off
		Pageable pageable = PageRequest.of(getRequest.getPage(),
							getRequest.getSize(),
							Sort.by(Sort.Direction.ASC, getRequest.getField()));


		Slice<Seller> slicedSellers = null;

		// @formatter:off
		if(getRequest.getSearchField().equals("name"))
		{
			slicedSellers = sellerRepository.findSellerByFirstNameLikeOrLastNameLike
			("%"+getRequest.getSearchPattern()+"%","%"+getRequest.getSearchPattern()+"%"
							, pageable);
		}

		else if(getRequest.getSearchField().equals("username"))
		{
			slicedSellers = sellerRepository.findSellerByUsernameLike
					("%"+getRequest.getSearchPattern()+"%", pageable);
		}

		else if(getRequest.getSearchField().equals("authorized"))
		{
			slicedSellers = sellerRepository.findSellerByFields(
					getRequest.isAuthorized(),
					getRequest.isProhibited(),
					getRequest.getSearchPattern(),
					pageable);

		}

		List<Seller> sellers = null;
		if(!slicedSellers.isEmpty())
		{
			 sellers = slicedSellers.getContent();

		}



		return sellers;
	}

	@Override
	public Seller getSellerById(int id)
	{
		Optional<Seller> seller = sellerRepository.findById(id);

		if(seller.isEmpty())
		{
			throw new NotFoundException("SELLER WITH ID: " + id);
		}

		return seller.get();
	}

	@Override
	public Seller addSeller(Seller seller)
	{
		// check if the username is already taken and if the email is taken

		if(!validationService.isEmailNotTaken(seller.getSellerDetail().getEmail()))
		{
			throw new UniqueErrorException("Email ");
		}

		if(!validationService.isUsernameNotTaken(seller.getUsername()))
		{
			throw new UniqueErrorException("Username ");
		}

		/*
		 * set seller id to 0 to invoke insert function
		 * not update
		 *
		 * then instantiate creation date
		 *
		 * then encrypt password
		 */

		seller.setId(0);

		try
		{
			/* creating connection from store to seller */
			seller.getStore().setId(0);
			seller.getStore().setSeller(seller);
		}
		finally
		{
			seller.setPassword(bcrypt.encode(seller.getPassword()));
		}

			sellerRepository.save(seller);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", seller.getFirstName() + " " + seller.getLastName());
			model.put("body", EmailMessages.registrationMessage("SELLER"));
			try
			{
				emailSender.sendEmailWithTemplate(
						model,
						seller.getSellerDetail().getEmail(),
						"Emarket Rider Registration");
			}
			catch (MessagingException | IOException | TemplateException e)
			{
				e.printStackTrace();
			}
			return seller;
	}

	@Override
	public Seller updateSeller(Seller seller)
	{
		/*
		 * Getting the password from the database and injecting it to the
		 * current seller
		 * passing the creation date to remain unchanged
		 * updating the modifiedDate
		 */

		Seller dbseller = getSellerById(seller.getId());

		if(dbseller == null)
		{
			throw new NotFoundException("SELLER WITH ID: " + seller.getId());
		}

		seller.setPassword(dbseller.getPassword());

		if(dbseller.getStore() == null)
		{
			/* creating connection from store to seller */
			seller.getStore().setId(0);
		}
		seller.getStore().setSeller(seller);


		return sellerRepository.save(seller);

	}

	@Override
	public void deleteSeller(Seller seller)
	{
		try
		{
			sellerRepository.delete(seller);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETE SELLER");
		}

	}

	@Override
	public void deleteSellerById(int id)
	{
		try
		{
			sellerRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETE SELLER WITH ID: " + id);
		}

	}

	@Override
	public Seller findSellerByUsername(String username)
	{
		try
		{
			return sellerRepository.findSellerByUsername(username);
		}
		catch (Exception e)
		{
			throw new NotFoundException("SELLER WITH USERNAME: " + username);
		}
	}

	@Override
	public int countSellerRequest()
	{
		return sellerRepository.countSellerRequest();
	}

	@Override
	public Seller loginSeller(Seller seller)
	{
		Seller dbSeller = findSellerByUsername(seller.getUsername());

		if(bcrypt.matches(seller.getPassword(), dbSeller.getPassword()))
		{
			if(dbSeller.getSellerDetail().isAuthorized() &&
					!dbSeller.getSellerDetail().isProhibited())
			{
				dbSeller.getSellerDetail().setStatus(true);
				sellerRepository.save(dbSeller);
				return dbSeller;
			}
			else
			{
				throw new FailedException("LOGIN");
			}
		}
		throw new ErrorLoginException("SELLER [Username, Password]");
	}

	@Override
	public Seller logoutSeller(Seller seller)
	{
		Seller dbSeller = findSellerByUsername(seller.getUsername());

		dbSeller.getSellerDetail().setStatus(false);
		sellerRepository.save(dbSeller);
		return dbSeller;
	}

	@Override
	public Seller changePass(PutRequestChangePassword changePass)
	{
		Seller dbSeller = getSellerById(changePass.getId());

		if(bcrypt.matches(changePass.getPassword(), dbSeller.getPassword()))
		{
			dbSeller.setPassword(bcrypt.encode(changePass.getNewPassword()));

			sellerRepository.save(dbSeller);
			return dbSeller;
		}

		throw new FailedException("UPDATING PASSWORD");
	}



}
