package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Basket;
import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestTransaction;

public interface TransactionService
{
	public List<Transaction> getTransaction();

	public List<Transaction> getTransaction(GetRequestTransaction getRequest);

	public Transaction getTransactionById(int id);

	public void addTransaction(List<Transaction> order);

	public void addTransaction(Transaction transaction);

	public void updateTransaction(Transaction transaction);

	public void deleteTransaction(Transaction transaction);

	public void deleteTransactionById(int id);

	public List<Transaction> checkout(List<Basket> baskets);

	public Transaction checkTransactionComplete(int id);

	public Transaction continueTransaction(Transaction transaction);

	public Transaction cancelTransaction(Transaction transaction);

	public Transaction assignRider(int id);

	public Transaction getRiderAssignment(int id);

	public Transaction onDelivery(int id);

	public Transaction arrived(int id);

	public Transaction toRate(int id);

	public Transaction completed(int id);

}
