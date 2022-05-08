package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.algorithms.ChildTransactionHistoryRemover;
import org.emarket.hustle.emarkethustle.algorithms.RecallibrateRatings;
import org.emarket.hustle.emarkethustle.dao.HistoryRepository;
import org.emarket.hustle.emarkethustle.entity.History;
import org.emarket.hustle.emarkethustle.entity.ItemReview;
import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestHistory;
import org.emarket.hustle.emarkethustle.response.FailedException;
import org.emarket.hustle.emarkethustle.response.NotFoundException;
import org.emarket.hustle.emarkethustle.response.NotificationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService
{
	Logger log = Logger.getLogger(HistoryServiceImpl.class.getName());
	@Autowired
	HistoryRepository historyRepository;

	@Autowired
	TransactionService transactionService;

	@Autowired
	RecallibrateRatings recallibrateRatings;

	@Autowired
	NotificationService notificationService;

	@Autowired
	ItemService itemService;

	@Override
	public List<History> getHistory()
	{
		List<History> histories = historyRepository.findAll();

		if(histories.isEmpty())
		{
			throw new NotFoundException("HISTORIES");
		}

		return histories;
	}

	@Override
	public List<History> getHistory(GetRequestHistory getRequest)
	{

		Pageable pageable = PageRequest.of(getRequest.getPage(),
				getRequest.getSize(),
				Sort.by(Sort.Direction.DESC, getRequest.getField()));

		Slice<History> slicedHistories = null;

		if(getRequest.getUser().equals("Store"))
		{
			return historyRepository.findHistoryByStoreIdOrderByIdDesc(getRequest.getId());
		}
		else
		{
			slicedHistories = historyRepository.findHistoryByItemId(getRequest.getId(), pageable);
		}

		List<History> histories = slicedHistories.getContent();

		if(histories.isEmpty())
		{
			throw new NotFoundException("ITEMS");
		}

		return histories;

	}

	@Override
	public History getHistoryById(int id)
	{
		Optional<History> history = historyRepository.findById(id);

		if(history.isEmpty())
		{
			throw new NotFoundException("HISTORY WITH ID: " + id);
		}

		return ChildTransactionHistoryRemover.removeHistoryFromChildTransaction(history.get());
	}

	@Override
	public void saveHistory(History history)
	{
		try
		{
			historyRepository.save(history);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("SAVING HISTORY");

		}

	}

	@Override
	public void deleteHistory(History history)
	{
		try
		{
			historyRepository.delete(history);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("DELETING HISTORY WITH ID: " + history.getId());
		}

	}

	@Override
	public void deleteHistoryById(int id)
	{
		try
		{
			historyRepository.deleteById(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new FailedException("DELETING HISTORY WITH ID: " + id);
		}

	}

	@Override
	public void updateHistoryStatus(String value, int id)
	{
		Optional<History> getHistory = historyRepository.findById(id);

		History hist = getHistory.get();

		log.info("Hello from updateHistory");

		if(hist.getTransaction().getOrderType().equals("Pick Up")
				&& value.equals("Ready"))
		{
			Transaction transaction = transactionService.getTransactionById(hist.getTransaction().getId());

			for (History history : transaction.getHistories())
			{
				log.info(history + " History");
			}

			int count = 0;
			for (History history : transaction.getHistories())
			{
				if(hist.getId() == history.getId())
				{
					log.info("Same Id");
					hist.setStatus(value);
				}
				else if(hist.getStatus().equals("Preparing"))
				{
					log.info("Some Status still Preparing");
					count++;
				}
			}

			if(count == 0)
			{
				transaction.setStatus("Ready");
			}

			transactionService.updateTransaction(transaction);

			notificationService.addNotification(NotificationMessages.readyForPickup(transaction));
			return;
		}

		hist.setStatus(value);
		saveHistory(hist);
		log.info("History with id: " + hist.getId() + " is updating");

	}

	@Override
	public History rateHistory(ItemReview itemReview, int id)
	{
		log.info("Rating Item");

		Optional<History> getHistory = historyRepository.findById(id);

		History history = getHistory.get();
		Transaction transaction = transactionService.getTransactionById(history.getTransaction().getId());

		int count = 0;
		log.info("history : " + history.toString());
		for (History hist : transaction.getHistories())
		{
			if(hist.getId() == id)
			{
				log.info("Same Id");
				hist.setStatus("Completed");
				hist.setItemReview(itemReview);
				history = hist;
			}
			else if(!hist.getStatus().equals("Completed"))
			{
				log.info("Some History still needs to be rated");
				count++;
			}
		}

		log.info("count: " + count);
		if(count == 0)
		{
			transaction.setStatus("Completed");
		}

		log.info("transaction status: " + transaction.getStatus());
		transactionService.updateTransaction(transaction);
		recallibrateRatings.recalibrateOverallRating(history.getItem());
		notificationService.addNotification(NotificationMessages.itemRated(history));
		return history;
	}

}
