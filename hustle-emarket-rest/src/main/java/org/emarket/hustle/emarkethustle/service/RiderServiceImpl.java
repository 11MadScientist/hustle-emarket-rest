package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.RiderRepository;
import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiderServiceImpl implements RiderService
{

	@Autowired
	RiderRepository riderRepository;

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
	public Rider saveRider(Rider rider)
	{
		try
		{
			return riderRepository.save(rider);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING RIDER");
		}

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
	public Rider loginRider(String username)
	{
		try
		{
			return riderRepository.findByUsername(username);
		}
		catch (Exception e)
		{
			throw new NotFoundException("CUSTOMER WITH USERNAME" + username);
		}

	}

	@Override
	public int countRiderRequest()
	{
		return riderRepository.countRiderRequest();
	}

}
