package org.emarket.hustle.emarkethustle.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Basket;
import org.emarket.hustle.emarkethustle.entity.Transaction;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestTransaction;
import org.emarket.hustle.emarkethustle.response.ProcessConfirmation;
import org.emarket.hustle.emarkethustle.service.TransactionService;
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
public class TransactionRestController
{
	Logger log = Logger.getLogger(TransactionRestController.class.getName());

	@Autowired
	TransactionService transactionService;

	/*
	 * #######################################
	 * ############# GET TRANSACTION ###############
	 * #######################################
	 */

	@GetMapping("/transactions")
	public List<Transaction> getTransaction(
			@RequestParam(name = "user", required = false) String userProfile,
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "page", required = false) Integer page)
	{
		if(userProfile == null)
		{
			return transactionService.getTransaction("");
		}
		GetRequestTransaction getRequest = new GetRequestTransaction();
		log.info(userProfile + " has requested get transaction.");
		getRequest.setUserProfile(userProfile);
		getRequest.setUserId(id);
		if(page != null)
		{
			getRequest.setPage(page);
		}
		return transactionService.getTransaction(getRequest);
	}

	/*
	 * #######################################
	 * ########### GET TRANSACTION BY ID #####
	 * #######################################
	 */

	@GetMapping("/transactions/{id}")
	public Transaction getTransactionById(@PathVariable int id)
	{
		return transactionService.getTransactionById(id);
	}

	/*
	 * #######################################
	 * ############# ADD TRANSACTION #########
	 * #######################################
	 */

	@PostMapping("/transactions")
	public List<Transaction> addTransactions(@RequestBody List<Transaction> transactions)
	{

		transactionService.addTransaction(transactions);

		return transactions;
	}

	/*
	 * #######################################
	 * ########### UPDATE TRANSACTION ##############
	 * #######################################
	 */

	@PutMapping("/transactions")
	public Transaction updateTransaction(@RequestBody Transaction transaction)
	{
		transactionService.updateTransaction(transaction);
		return transaction;
	}

	/*
	 * #######################################
	 * ########### DELETE HISTORY ############
	 * #######################################
	 */

	@DeleteMapping("/transactions")
	public ProcessConfirmation deleteTransaction(@RequestBody Transaction transaction)
	{
		transactionService.deleteTransaction(transaction);

		return new ProcessConfirmation("SUCCESS", "TRANSACTION",
				"THE TRANSACTION WITH ID:" + transaction.getId() + " WAS DELETED.");
	}

	@DeleteMapping("/transactions/{id}")
	public ProcessConfirmation deleteTransaction(@PathVariable int id)
	{
		transactionService.deleteTransactionById(id);
		return new ProcessConfirmation("SUCCESS", "TRANSACTION",
				"THE TRANSACTION WITH ID:" + id + " WAS DELETED.");
	}

	/*
	 * #############################################################
	 * ############# PREPARE TRANSACTION FOR CHECHKOUT #############
	 * #############################################################
	 */

	@PutMapping("/transactions/checkouts")
	public List<Transaction> checkout(@RequestBody List<Basket> baskets)
	{
		return transactionService.checkout(baskets);
	}

	/*
	 * #############################################################
	 * ########### CHECK IF TRANSACTION IS COMPLETED ##############
	 * #############################################################
	 */

	@GetMapping("/transactions/status/{id}")
	public Transaction checkTransactionStatus(@PathVariable int id)
	{
		return transactionService.checkTransactionComplete(id);
	}

	/*
	 * ###############################################
	 * ########### CONTINUE TRANSACTION ##############
	 * ###############################################
	 */

	@PutMapping("/transactions/customers/continue")
	public Transaction continueTransaction(@RequestBody Transaction transaction)
	{
		log.info("Continuing transaction: " + transaction.getId());
		return transactionService.continueTransaction(transaction);
	}

	/*
	 * ###############################################
	 * ############# CANCEL TRANSACTION ##############
	 * ###############################################
	 */

	@PutMapping("/transactions/customers/cancel")
	public Transaction cancelTransaction(@RequestBody Transaction transaction)
	{
		log.info("Cancelling transaction: " + transaction.getId());
		return transactionService.cancelTransaction(transaction);
	}

	/*
	 * ###############################################
	 * ################ ASSIGN RIDER #################
	 * ###############################################
	 */

	@GetMapping("/transactions/customers/assign/{id}")
	public Transaction assignRider(@PathVariable int id)
	{
		return transactionService.assignRider(id);
	}

	/*
	 * ###############################################
	 * ########### GET ASSIGNMENT RIDER ##############
	 * ###############################################
	 */

	@GetMapping("/transactions/riders/assign/{id}")
	public Transaction getAssignment(@PathVariable int id)
	{
		return transactionService.getRiderAssignment(id);
	}

	/*
	 * ###############################################
	 * ############## TO DELIVER RIDER ###############
	 * ###############################################
	 */

	@GetMapping("/transactions/riders/deliver/{id}")
	public Transaction onDelivery(@PathVariable int id)
	{
		return transactionService.onDelivery(id);
	}

	/*
	 * ###############################################
	 * ############### ARRIVED RIDER #################
	 * ###############################################
	 */

	@GetMapping("/transactions/riders/arrive/{id}")
	public Transaction arrived(@PathVariable int id)
	{
		return transactionService.arrived(id);
	}

	/*
	 * ###############################################
	 * ############## CLAIM CUSTOMER #################
	 * ###############################################
	 */

	@GetMapping("/transactions/customers/claim/{id}")
	public Transaction claim(@PathVariable int id)
	{
		return transactionService.claim(id);
	}

	/*
	 * ###############################################
	 * ############# TO RATE CUSTOMER ################
	 * ###############################################
	 */
	@GetMapping("/transactions/customers/rate/{id}")
	public Transaction toRate(@PathVariable int id)
	{
		return transactionService.toRate(id);
	}

	/*
	 * ###############################################
	 * ############# COMPLETED CUSTOMER ##############
	 * ###############################################
	 */

//	@GetMapping("/transactions/customer/complete/{id}")
//	public Transaction completed(@PathVariable int id)
//	{
//		return transactionService.completed(id);
//	}

}
