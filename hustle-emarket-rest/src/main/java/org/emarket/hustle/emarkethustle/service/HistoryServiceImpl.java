package org.emarket.hustle.emarkethustle.service;

import java.util.List;
import java.util.Optional;

import org.emarket.hustle.emarkethustle.algorithms.RecallibrateRatings;
import org.emarket.hustle.emarkethustle.dao.HistoryRepository;
import org.emarket.hustle.emarkethustle.entity.History;
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
	@Autowired
	HistoryRepository historyRepository;

	@Autowired
	TransactionService transactionService;

	@Autowired
	RecallibrateRatings recallibrateRatings;

	@Autowired
	NotificationService notificationService;

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
			System.out.println(getRequest.getId());
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
		return history.get();
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
		History history = getHistoryById(id);
		history.setStatus(value);
		saveHistory(history);

		if(history.getTransaction().getOrderType().equals("Pick Up")
				&& value.equals("Ready"))
		{
			for (History hist : history.getTransaction().getHistories())
			{
				if(!hist.getStatus().equals("Ready"))
				{
					return;
				}
			}
			history.getTransaction().setStatus("Ready");
			transactionService.updateTransaction(history.getTransaction());
			notificationService.addNotification(NotificationMessages.readyForPickup(history.getTransaction()));
		}

	}

	@Override
	public History rateHistory(History history)
	{
		saveHistory(history);
		notificationService.addNotification(NotificationMessages.itemRated(history));
		recallibrateRatings.recalibrateOverallRating(history.getItem());
		return history;
	}

}
