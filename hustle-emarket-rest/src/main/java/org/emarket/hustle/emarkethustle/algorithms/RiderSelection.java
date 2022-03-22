package org.emarket.hustle.emarkethustle.algorithms;

import java.util.LinkedList;
import java.util.Queue;

import org.emarket.hustle.emarkethustle.entity.Rider;

public class RiderSelection
{
	private Queue<Rider> riders = new LinkedList<Rider>();

	public boolean isNull()
	{
		return riders.isEmpty();
	}

	public void enqueueRider(Rider rider)
	{
		riders.add(rider);
	}

	public Rider dequeueRider()
	{
		return riders.poll();
	}

	public void removeRider(Rider rider)
	{
		riders.remove(rider);
	}

	public void printRiders()
	{
		System.out.println(riders);
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
