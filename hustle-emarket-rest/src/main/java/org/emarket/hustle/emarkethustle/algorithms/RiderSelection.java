package org.emarket.hustle.emarkethustle.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.Transaction;

public class RiderSelection
{
	private Queue<Rider> riders = new LinkedList<Rider>();

	private List<Transaction> transactions = new ArrayList<>();

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

	public void addTransaction(Transaction transaction)
	{
		transactions.add(transaction);
	}

	public void removeTransaction(Transaction transaction)
	{
		transactions.remove(transaction);
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
