package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.entity.request.PutRequestChangePassword;

public interface RiderService
{
	public List<Rider> getRiders(GetRequestUser getRequestUser);

	public List<Rider> getRiders();

	public Rider getRiderById(int id);

	public Rider addRider(Rider rider);

	public Rider updateRider(Rider rider);

	public void deleteRider(Rider rider);

	public void deleteRiderById(int id);

	public Rider findRiderByUsername(String username);

	public int countRiderRequest();

	public Rider availableRider(Rider rider);

	Rider loginRider(Rider rider);

	Rider logoutRider(Rider rider);

	Rider changePass(PutRequestChangePassword request);

}
