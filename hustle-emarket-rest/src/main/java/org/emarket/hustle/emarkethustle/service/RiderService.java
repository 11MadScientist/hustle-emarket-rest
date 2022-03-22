package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;

public interface RiderService
{
	public List<Rider> getRiders(GetRequestUser getRequestUser);

	public List<Rider> getRiders();

	public Rider getRiderById(int id);

	public Rider saveRider(Rider rider);

	public void deleteRider(Rider rider);

	public void deleteRiderById(int id);

	public Rider loginRider(String username);

	public Rider findRiderByUsername(String username);

	public int countRiderRequest();

	public Rider availableRider(Rider rider);

}
