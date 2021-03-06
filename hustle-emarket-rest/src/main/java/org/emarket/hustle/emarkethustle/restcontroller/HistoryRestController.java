package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.algorithms.ChildTransactionHistoryRemover;
import org.emarket.hustle.emarkethustle.entity.History;
import org.emarket.hustle.emarkethustle.entity.ItemReview;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestHistory;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.basePath}")
public class HistoryRestController
{
	@Autowired
	HistoryService historyService;

	Logger log = Logger.getLogger(HistoryRestController.class.getName());

	/*
	 * #######################################
	 * ########### GET HISTORY ###############
	 * #######################################
	 */

	@GetMapping("/histories")
	public List<History> getHistory(
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "user", defaultValue = "Store") String user,
			@RequestParam(name = "page", defaultValue = "1") Integer page)
	{

		if(id == null)
		{
			return ChildTransactionHistoryRemover.removeHistoryFromChildTransaction(historyService.getHistory());
		}
		GetRequestHistory getRequest = new GetRequestHistory();
		getRequest.setId(id);
		getRequest.setUser(user);
		getRequest.setPage(page);

		return ChildTransactionHistoryRemover.removeHistoryFromChildTransaction(historyService.getHistory(getRequest));
	}

	/*
	 * #######################################
	 * ######## GET HISTORY BY ID ############
	 * #######################################
	 */

	@GetMapping("/histories/{id}")
	public History getHistoryById(@PathVariable int id)
	{
		return ChildTransactionHistoryRemover.removeHistoryFromChildTransaction(historyService.getHistoryById(id));
	}

	/*
	 * #######################################
	 * ############# ADD HISTORY ###############
	 * #######################################
	 */

	@PostMapping("/histories")
	public ProcessConfirmation addHistory()
	{
		return new ProcessConfirmation("FAILED", "HISTORY", "UNSUPPORTED ENDPOINT");
	}

	/*
	 * #######################################
	 * ########### UPDATE HISTORY ############
	 * #######################################
	 */

	@PutMapping("/histories")
	public History updateHistory(@RequestBody History history)
	{
		historyService.saveHistory(history);
		return history;
	}

	/*
	 * #######################################
	 * ########### DELETE HISTORY ############
	 * #######################################
	 */

	@DeleteMapping("/histories")
	public ProcessConfirmation deleteHistory(@RequestBody History history)
	{
		historyService.deleteHistory(history);

		return new ProcessConfirmation("SUCCESS", "HISTORY",
				"THE HISTORY WITH ID:" + history.getId() + " WAS DELETED.");
	}

	@DeleteMapping("/histories/{id}")
	public ProcessConfirmation deleteHistory(@PathVariable int id)
	{
		historyService.deleteHistoryById(id);
		return new ProcessConfirmation("SUCCESS", "HISTORY",
				"THE HISTORY WITH ID:" + id + " WAS DELETED.");
	}

	/*
	 * #######################################
	 * ###### UPDATE HISTORY STATUS ##########
	 * #######################################
	 */

	@PostMapping("/histories/status")
	public ProcessConfirmation updateStatus(
			@RequestBody History history)
	{
		log.info("Hello");
		historyService.updateHistoryStatus(history.getStatus(), history.getId());
		return new ProcessConfirmation("SUCCESS", "HISTORY",
				"THE HISTORY WITH ID: " + history.getId() + " WAS " + history.getStatus().toUpperCase());
	}

	@PostMapping("/histories/rate/{id}")
	public ProcessConfirmation rateHistory(
			@PathVariable int id,
			@RequestBody ItemReview itemReview)
	{
		historyService.rateHistory(itemReview, id);
		return new ProcessConfirmation("SUCCESS", "HISTORY",
				"THE ITEM WITH ID: " + itemReview.getItem().getId() + " WAS " + "RATED SUCCESSFULLY");
	}

}
