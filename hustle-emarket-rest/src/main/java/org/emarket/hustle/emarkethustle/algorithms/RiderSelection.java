package org.emarket.hustle.emarkethustle.algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.emarket.hustle.emarkethustle.entity.Rider;

public class RiderSelection
{
	HashMap<String, Queue<Rider>> riderStations = new HashMap<String, Queue<Rider>>();

//	private Queue<Rider> riders = new LinkedList<Rider>();

	public boolean isStationNull(String station)
	{
		System.out.println("Station: " + station);
		System.out.println(riderStations.containsKey(station));
		return !riderStations.containsKey(station);
	}

	public void enqueueRider(Rider rider)
	{
		String station = rider.getRiderDetail().getStation();
		if(!riderStations.containsKey(station))
		{
			riderStations.put(station, new LinkedList<Rider>());
		}

		riderStations.get(station).add(rider);

	}

	public Rider dequeueRider(String station)
	{
		return riderStations.get(station).poll();
	}

	public void removeRider(Rider rider)
	{
		String station = rider.getRiderDetail().getStation();
		riderStations.get(station).remove(rider);
	}

	public void printRiders()
	{
		for (String key : riderStations.keySet())
		{
			System.out.println(key + ": ");
			System.out.println(riderStations.get(key));
		}
	}

	private static RiderSelection riderSelection;

	private RiderSelection()
	{
//		this class is a singleton
	}

	public static RiderSelection getInstance()
	{
		if(riderSelection == null)
		{
			riderSelection = new RiderSelection();
		}

		return riderSelection;
	}

}
