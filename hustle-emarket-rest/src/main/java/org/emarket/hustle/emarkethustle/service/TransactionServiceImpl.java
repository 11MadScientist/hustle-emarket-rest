package org.emarket.hustle.emarkethustle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.emarket.hustle.emarkethustle.algorithms.RiderSelection;
import org.emarket.hustle.emarkethustle.dao.BasketRepository;
import org.emarket.hustle.emarkethustle.dao.ItemRepository;
import org.emarket.hustle.emarkethustle.dao.TransactionRepository;
import org.emarket.hustle.emarkethustle.entity.Basket;
import org.emarket.hustle.emarkethustle.entity.History;
import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestTransaction;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService
{

	RiderSelection riderSelection = RiderSelection.getInstance();

	Logger log = Logger.getLogger(TransactionServiceImpl.class.getName());
	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	BasketRepository basketRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	RiderService riderService;

	@Override
	public List<Transaction> getTransaction()
	{
		log.info("From GetTransaction");
		List<Transaction> transactions = transactionRepository.findAll();

		if(transactions.isEmpty())
		{
			throw new NotFoundException("TRANSACTIONS");
		}

		return transactions;
	}

	@Override
	@Transactional
	public List<Transaction> getTransaction(GetRequestTransaction getRequest)
	{
		// @formatter:off
		Pageable pageable = PageRequest.of(getRequest.getPage(),
							getRequest.getSize(),
							Sort.by(Sort.Direction.DESC, getRequest.getField()));

		Slice<Transaction> slicedTransactions = null;

		if(getRequest.getUserProfile() == "Customer")
		{
			slicedTransactions = transactionRepository.findByCustomer(getRequest.getUserId(), pageable);
		}
		else if(getRequest.getUserProfile() == "Rider")
		{
			slicedTransactions = transactionRepository.findByRider(getRequest.getUserId(), pageable);
		}

		List<Transaction> transactions = slicedTransactions.getContent();

		if(transactions.isEmpty())
		{
			throw new NotFoundException("TRANSACTIONS");
		}

		return transactions;
	}

	@Override
	public Transaction getTransactionById(int id)
	{
		Optional<Transaction> transaction = transactionRepository.findById(id);

		if(transaction.isEmpty())
		{
			throw new NotFoundException("ITEM WITH ID: " + id);
		}
		return transaction.get();
	}

	@Override
	@Transactional
	public void saveTransaction(List<Transaction> transactions)
	{
		log.info("list transaction");
		try
		{
			List<History> histories = new ArrayList<>();
			for (int i = 0; i < transactions.size(); i++)
			{
				histories = transactions.get(i).getHistories();

				for (int x = 0; x < histories.size(); x++)
				{

					histories.get(x).setTransaction(transactions.get(i));
				}
			}

			transactionRepository.saveAll(transactions);

			// i think basket of customer should be delete here
			basketRepository.deleteByCustomerId(transactions.get(0).getCustomer().getId());

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("SAVING TRANSACTIONS");

		}

	}

	@Override
	public void saveTransaction(Transaction transaction)
	{
		log.info("single transaction");
		try
		{
			transactionRepository.save(transaction);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("SAVING TRANSACTIONS");

		}

	}

	@Override
	public void deleteTransaction(Transaction transaction)
	{
		try
		{
			transactionRepository.delete(transaction);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING TRANSACTION");
		}

	}

	@Override
	public void deleteTransactionById(int id)
	{
		try
		{
			transactionRepository.deleteById(id);
		}
		catch (Exception e)
		{
			throw new FailedException("DELETING TRANSACTION WITH ID: " + id);
		}

	}

	@Value("${service.fee}")
	private double serviceFee;

	@Value("${delivery.fee}")
	private double deliveryFee;

//	this checkout method will convert basket into histories, stick it to
//	transaction, prepare the transaction, assign the service fee
//	and delivery fee calculate the grand total and return the transaction
//	but the transaction is not yet saved.

	@Override
	public List<Transaction> checkout(List<Basket> baskets)
	{
		log.info("from checkout");

		List<Transaction> transactions = new ArrayList<>();

		while (!baskets.isEmpty())
		{
			Transaction transaction = new Transaction();
			String transactionPlace = baskets.get(0).getStoreAddress();

			int i = 0;

			while (i < baskets.size())
			{
				Basket basket = baskets.get(i);
				log.info(basket.getStoreAddress().equals(transactionPlace) + "");
				if(basket.getStoreAddress().equals(transactionPlace))
				{
					log.info("hello from innerloop " + baskets.size());
					History history = new History(0, transaction, basket.getStore(),
							basket.getItem(), basket.getItem().getName(),
							basket.getItem().getPrice(), basket.getQuantity());

					transaction.addSubTotal(history.getQuantity() * history.getPrice());
					transaction.addHistory(history);

					basketRepository.save(basket);
					baskets.remove(i);
				}
				log.info(i+"increment");
				i++;
			}

			transaction.setStation(transactionPlace);
			transaction.setServiceFee(serviceFee);
			transaction.setDeliveryFee(deliveryFee);
			transaction.setGrandTotal();
			transactions.add(transaction);
		}

		log.info(transactions.size() + "");
		return transactions;

	}

	//checkTransactionComplete - the client will request this every 5 sec to update,
	//if there are buyers who declined

	@Override
	public Transaction checkTransactionComplete(int id)
	{
		log.info("hello from the checkTransactionComplete");
		Transaction transaction = getTransactionById(id);
		int declined = 0;

		for(History history:transaction.getHistories())
		{
			if(history.getStatus().equals("Pending"))
			{
				return transaction;
			}
		}
		for(History history:transaction.getHistories())
		{

			if(history.getStatus().equals("Declined"))
			{
				transaction.setSubTotal(transaction.getSubTotal() - history.getQuantity() * history.getPrice());
				declined ++;
			}
		}

		if(declined != 0)
		{
			transaction.setGrandTotal();
			return transaction;
		}

		log.info("hello, no Pending, or declined");
		for(History history:transaction.getHistories())
		{
			history.setStatus("Preparing");
		}
		transaction.setStatus("Preparing");

		saveTransaction(transaction);
		return transaction;

	}

	@Override
	public Transaction continueTransaction(Transaction transaction)
	{
		for(History history:transaction.getHistories())
		{
			if(history.getStatus().equals("Accepted"))
			{
				history.setStatus("Preparing");
			}
		}

		saveTransaction(transaction);
		return transaction;
	}

	@Override
	public Transaction cancelTransaction(Transaction transaction)
	{
		for(History history:transaction.getHistories())
		{
			history.setStatus("Cancelled");

		}
		transaction.setStatus("Cancelled");

		return transaction;
	}


	//request for rider to pickup their order
	@Override
	public Transaction assignRider(int id)
	{
		Transaction transaction = getTransactionById(id);
		if(riderSelection.isStationNull(transaction.getStation()))
		{
			System.out.println("it was null");
			return null;
		}

		System.out.print("before assignment: " );
		riderSelection.printRiders();

//		you need to implement notification for the rider here.
		Rider rider = riderSelection.dequeueRider(transaction.getStation());
		rider.setStatus("Occupied");
		riderService.saveRider(rider);

		transaction.setRider(rider);
		saveTransaction(transaction);
		System.out.print("after assignment: " );
		riderSelection.printRiders();
		return transaction;

	}

	@Override
	public Transaction onDelivery(int id)
	{
		Transaction transaction = getTransactionById(id);

		transaction.setStatus("On Delivery");
		saveTransaction(transaction);
		return transaction;
	}

	@Override
	public Transaction arrived(int id)
	{
		Transaction transaction = getTransactionById(id);

		transaction.setStatus("Arrived");
		saveTransaction(transaction);
		return transaction;
	}

	@Override
	public Transaction toRate(int id)
	{
		Transaction transaction = getTransactionById(id);

		transaction.setStatus("To Rate");
		saveTransaction(transaction);
		return transaction;
	}

	@Override
	public Transaction completed(int id)
	{
		Transaction transaction = getTransactionById(id);

		transaction.setStatus("Completed");
		saveTransaction(transaction);
		return transaction;
	}






}
