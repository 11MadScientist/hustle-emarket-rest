package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestTransaction;

public interface TransactionService
{
	public List<Transaction> getTransaction();

	public List<Transaction> getTransaction(GetRequestTransaction getRequest);

	public Transaction getTransactionById(int id);

	public void saveTransaction(List<Transaction> order);

	public void saveTransaction(Transaction order);

	public void deleteTransaction(Transaction order);

	public void deleteTransactionById(int id);

	public List<Transaction> checkout(int id);

}
