package org.emarket.hustle.emarkethustle.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;

import org.emarket.hustle.emarkethustle.algorithms.RiderSelection;
import org.emarket.hustle.emarkethustle.dao.RiderRepository;
import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;
import org.emarket.hustle.emarkethustle.response.EmailMessages;
import org.emarket.hustle.emarkethustle.response.ErrorLoginException;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.NotPermittedException;
import org.emarket.hustle.emarkethustle.response.UniqueErrorException;
import org.emarket.hustle.emarkethustle.security.BcryptSecurity;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;

@Service
public class RiderServiceImpl implements RiderService
{
	Logger log = Logger.getLogger(RiderServiceImpl.class);

//	for duplicate validation
	@Autowired
	private ValidationService validationService;

	@Autowired
	RiderRepository riderRepository;

	@Autowired
	EmailSenderService emailSender;

	// for the bean bCrypt
	@Autowired
	private BcryptSecurity bcrypt;

	RiderSelection riderSeletion = RiderSelection.getInstance();

	@Override
	public List<Rider> getRiders(GetRequestUser getRequestUser)
	{
		return riderRepository.findRider(
				getRequestUser.isAuthorized(),
				getRequestUser.isProhibited(),
				getRequestUser.getSearchPattern());
	}

	@Override
	public List<Rider> getRiders()
	{
		return riderRepository.findAll();
	}

	@Override
	public Rider getRiderById(int id)
	{
		Optional<Rider> result = riderRepository.findById(id);

		if(result.isEmpty())
		{
			throw new NotFoundException("RIDER WITH ID: " + id);
		}

		return result.get();
	}

	@Override
	public Rider findRiderByUsername(String username)
	{
		return riderRepository.findByUsername(username);
	}

	@Override
	public Rider addRider(Rider rider)
	{
		// check if the username is already taken and if the email is taken

		if(!validationService.isEmailNotTaken(rider.getRiderDetail().getEmail()))
		{
			throw new UniqueErrorException("Email ");
		}

		if(!validationService.isUsernameNotTaken(rider.getUsername()))
		{
			throw new UniqueErrorException("Username ");
		}

		rider.setId(0);

		/* encrypting password using bcrypt */
		rider.setPassword(bcrypt.encode(rider.getPassword()));
		riderRepository.save(rider);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", rider.getFirstName() + " " + rider.getLastName());
		model.put("body", EmailMessages.registrationMessage("RIDER"));
		try
		{
			emailSender.sendEmailWithTemplate(
					model,
					rider.getRiderDetail().getEmail(),
					"Emarket Rider Registration");
		}
		catch (MessagingException | IOException | TemplateException e)
		{
			e.printStackTrace();
		}
		log.info("Rider " + rider.getFirstName() + " " + rider.getLastName() + " has registered");
		return rider;

	}

	@Override
	public Rider updateRider(Rider rider)
	{
		Rider dbRider = getRiderById(rider.getId());

		if(dbRider == null)
		{
			throw new NotFoundException("RIDER WITH ID: " + rider.getId());
		}

		rider.setPassword(dbRider.getPassword());

		riderRepository.save(rider);

		return rider;

	}

	@Override
	public void deleteRider(Rider rider)
	{
		try
		{
			riderRepository.delete(rider);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING RIDER");
		}

	}

	@Override
	public void deleteRiderById(int id)
	{
		try
		{
			riderRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING RIDER WITH ID " + id + " ");
		}

	}

	@Override
	public int countRiderRequest()
	{
		return riderRepository.countRiderRequest();
	}

	@Override
	public Rider availableRider(Rider rider)
	{
		Rider dbRider = getRiderById(rider.getId());
		dbRider.setStatus("Available");
		riderRepository.save(dbRider);
		riderSeletion.enqueueRider(dbRider);
		return dbRider;
	}

	@Override
	public Rider loginRider(Rider rider)
	{
		log.info("Rider with username: " + rider.getUsername() + " is trying to login");
		Rider dbrider;
		try
		{
			dbrider = riderRepository.findByUsername(rider.getUsername());
		}
		catch (Exception e)
		{
			throw new NotFoundException("RIDER WITH USERNAME" + rider.getUsername());
		}

		if(dbrider.getRiderDetail().isAuthorized() == false ||
				dbrider.getRiderDetail().isProhibited() == true)
		{
			throw new NotPermittedException("RIDER WITH USERNAME " + rider.getUsername());
		}

		else if(bcrypt.matches(rider.getPassword(), dbrider.getPassword()))
		{
			return dbrider;
		}
		throw new ErrorLoginException("RIDER [Username, Password]");

	}

	@Override
	public Rider logoutRider(Rider rider)
	{
		Rider dbRider = findRiderByUsername(rider.getUsername());
		riderSeletion.removeRider(dbRider);
		dbRider.setStatus("Offline");
		riderRepository.save(dbRider);
		return dbRider;
	}

	@Override
	public Rider changePass(PutRequestChangePassword changePass)
	{
		Rider dbRider = getRiderById(changePass.getId());

		if(bcrypt.matches(changePass.getPassword(), dbRider.getPassword()))
		{
			dbRider.setPassword(bcrypt.encode(changePass.getNewPassword()));

			riderRepository.save(dbRider);
			return dbRider;
		}

		throw new FailedException("UPDATING PASSWORD");
	}

}
