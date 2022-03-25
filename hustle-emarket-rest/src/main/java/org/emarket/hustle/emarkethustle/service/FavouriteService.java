package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Favourite;

public interface FavouriteService
{
	public List<Favourite> getFavouritesByCustomerId(int id);

	public Favourite getFavourite(int id);

	public Favourite addFavourite(Favourite favourite);

	public void deleteFavoriteById(int id);
}
