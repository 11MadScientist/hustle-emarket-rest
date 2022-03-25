package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.dao.FavouriteRepository;
import org.emarket.hustle.emarkethustle.entity.Favourite;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteServiceImpl implements FavouriteService
{
	@Autowired
	private FavouriteRepository favouriteRepository;

	@Override
	public List<Favourite> getFavouritesByCustomerId(int id)
	{
		try
		{
			return favouriteRepository.findByCustomerId(id);
		}
		catch (Exception e)
		{
			throw new NotFoundException("FAVOURITES WITH CUSTOMER ID " + id);
		}
	}

	@Override
	public Favourite getFavourite(int id)
	{
		Optional<Favourite> result = favouriteRepository.findById(id);
		if(result.isEmpty())
		{
			throw new NotFoundException("FAVOURITES WITH ID " + id);
		}

		return result.get();
	}

	@Override
	public Favourite addFavourite(Favourite favourite)
	{
		try
		{
			return favouriteRepository.save(favourite);
		}
		catch (Exception e)
		{
			throw new FailedException("SAVING FAVOURITE");
		}
	}

	@Override
	public void deleteFavoriteById(int id)
	{
		try
		{
			favouriteRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING FAVOURITE");
		}

	}

}
