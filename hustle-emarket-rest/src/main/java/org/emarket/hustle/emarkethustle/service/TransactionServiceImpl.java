package org.emarket.hustle.emarkethustle.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.emarket.hustle.emarkethustle.dao.BasketRepository;
import org.emarket.hustle.emarkethustle.dao.ItemRepository;
import org.emarket.hustle.emarkethustle.dao.TransactionRepository;
import org.emarket.hustle.emarkethustle.entity.Basket;
import org.emarket.hustle.emarkethustle.entity.History;
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
	Logger log = Logger.getLogger(TransactionServiceImpl.class.getName());
	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	BasketRepository basketRepository;

	@Autowired
	ItemRepository itemRepository;

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

	@Override
	public List<Transaction> checkout(int id)
	{
		log.info(id + "from checkout");

		// recover customer basket from the database
		List<Basket> baskets = basketRepository.findByCustomerId(id);

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
					baskets.remove(i);
					continue;
				}

				i++;
			}


			transaction.setServiceFee(serviceFee);
			transaction.setDeliveryFee(deliveryFee);
			transaction.setGrandTotal();
			transactions.add(transaction);
		}

		log.info(transactions.size() + "");
		return transactions;

	}

}
