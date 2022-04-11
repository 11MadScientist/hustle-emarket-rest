package org.emarket.hustle.emarkethustle.algorithms;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.History;

public class ChildTransactionHistoryRemover
{
	public static List<History> removeHistoryFromChildTransaction(List<History> histories)
	{
		for (History history : histories)
		{
			history.getTransaction().setHistories(null);
		}
		return histories;
	}

	public static History removeHistoryFromChildTransaction(History history)
	{
		history.getTransaction().setHistories(null);
		return history;
	}
}
