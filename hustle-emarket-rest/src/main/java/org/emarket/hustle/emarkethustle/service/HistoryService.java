package org.emarket.hustle.emarkethustle.service;

import java.util.List;

import org.emarket.hustle.emarkethustle.entity.History;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestHistory;

public interface HistoryService
{
	public List<History> getHistory();

	public List<History> getHistory(GetRequestHistory getRequest);

	public History getHistoryById(int id);

	public void saveHistory(History history);

	public void deleteHistory(History history);

	public void deleteHistoryById(int id);

	void updateHistoryStatus(String value, int id);

}
