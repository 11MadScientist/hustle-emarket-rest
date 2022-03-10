package org.emarket.hustle.emarkethustle.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Customer;
import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.entity.Store;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestStore;
import org.emarket.hustle.emarkethustle.entity.request.GetRequestUser;
import org.emarket.hustle.emarkethustle.service.CustomerService;
import org.emarket.hustle.emarkethustle.service.RiderService;
import org.emarket.hustle.emarkethustle.service.SellerService;
import org.emarket.hustle.emarkethustle.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admins")
public class AdminController
{
	Logger log = Logger.getLogger(AdminController.class.getName());

	@Autowired
	StoreService storeService;

	@Autowired
	SellerService sellerService;

	@Autowired
	CustomerService customerService;

	@Autowired
	RiderService riderService;

	/*
	 * #######################################
	 * ############### Dashboard #############
	 * #######################################
	 */
	@GetMapping("/dashboard")
	public String adminPage(Model model)
	{

		GetRequestStore getRequestStore = new GetRequestStore();

		getRequestStore.setSearchPattern("");
		getRequestStore.setAuthorized(true);
		getRequestStore.setProhibited(false);
		getRequestStore.setSearchField("overallStock");

		List<Store> stores;
		int sellerRequestCount = 0;
		int storeCount = 0;
		int riderRequestCount = 0;
		try
		{
			sellerRequestCount = sellerService.countSellerRequest();
			storeCount = storeService.countStores();
			riderRequestCount = riderService.countRiderRequest();
			stores = storeService.getStore(getRequestStore);
		}
		catch (Exception e)
		{
			stores = new ArrayList<>();
		}

		model.addAttribute("sellerRequestCount", sellerRequestCount);
		model.addAttribute("riderRequestCount", riderRequestCount);
		model.addAttribute("storeCount", storeCount);
		model.addAttribute("stores", stores);

		return ("admins/dashboard");
	}

	/*
	 * #######################################
	 * ############ Seller Requests ##########
	 * #######################################
	 */
	@GetMapping("seller-requests")
	public String getSellerRequest(
			Model model,
			@RequestParam(value = "searchField", defaultValue = "") String searchField,
			@RequestParam(value = "field", defaultValue = "creationDate") String field,
			@RequestParam(value = "searchPattern", defaultValue = "") String searchPattern,
			@RequestParam(value = "authorized", defaultValue = "false") boolean authorized,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size)

	{
		GetRequestUser getRequestUser = new GetRequestUser();
		getRequestUser.setSearchField(searchField);
		getRequestUser.setField(field);
		getRequestUser.setSearchPattern(searchPattern);
		getRequestUser.setAuthorized(authorized);
		getRequestUser.setPage(page);
		getRequestUser.setSize(size);

		List<Seller> sellers;
		try
		{
			sellers = sellerService.getSeller(getRequestUser);
		}
		catch (Exception e)
		{
			sellers = new ArrayList<>();
		}

		model.addAttribute("searchPattern", getRequestUser.getSearchPattern());
		model.addAttribute("sellers", sellers);
		return ("admins/seller-request");
	}

	/*
	 * #######################################
	 * ############ Seller Verify ############
	 * #######################################
	 */

	@GetMapping("verify-seller")
	public String verifySeller(
			Model model,
			@RequestParam(value = "sellerId", required = true) int id)
	{

		Seller seller = sellerService.getSellerById(id);
		seller.getSellerDetail().setAuthorized(true);
		sellerService.saveSeller(seller);

		return "redirect:/admins/seller-requests?searchField=authorized&field=creationDate";
	}

	/*
	 * #######################################
	 * ############ Seller Prohibit ############
	 * #######################################
	 */

	@GetMapping("prohibit-seller")
	public String prohibitSeller(
			Model model,
			@RequestParam(value = "sellerId", required = true) int id)
	{

		Seller seller = sellerService.getSellerById(id);
		seller.getSellerDetail().setProhibited(!seller.getSellerDetail().isProhibited());
		sellerService.saveSeller(seller);

		return "redirect:/admins/seller-list?searchField=authorized&field=lastName"
				+ "&authorized=true&prohibited=" + !seller.getSellerDetail().isProhibited();
	}

	/*
	 * #######################################
	 * ############ Seller List ##############
	 * #######################################
	 */

	@GetMapping("seller-list")
	public String sellerList(
			Model model,
			@RequestParam(value = "searchField", defaultValue = "") String searchField,
			@RequestParam(value = "field", defaultValue = "creationDate") String field,
			@RequestParam(value = "searchPattern", defaultValue = "") String searchPattern,
			@RequestParam(value = "authorized", defaultValue = "true") boolean authorized,
			@RequestParam(value = "prohibited", defaultValue = "false") boolean prohibited,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size)

	{
		GetRequestUser getRequestUser = new GetRequestUser();
		getRequestUser.setSearchField(searchField);
		getRequestUser.setField(field);
		getRequestUser.setSearchPattern(searchPattern);
		getRequestUser.setAuthorized(authorized);
		getRequestUser.setProhibited(prohibited);
		getRequestUser.setPage(page);
		getRequestUser.setSize(size);

		List<Seller> sellers;
		try
		{
			sellers = sellerService.getSeller(getRequestUser);
		}
		catch (Exception e)
		{
			sellers = new ArrayList<>();
		}
		model.addAttribute("tableSwitch", getRequestUser.isProhibited());
		model.addAttribute("searchPattern", getRequestUser.getSearchPattern());

		model.addAttribute("sellers", sellers);
		return ("admins/seller-list");
	}

	/*
	 * #######################################
	 * ############ Customer List ############
	 * #######################################
	 */
	@GetMapping("customer-list")
	public String customerList(
			Model model,
			@RequestParam(value = "searchField", defaultValue = "name") String searchField,
			@RequestParam(value = "field", defaultValue = "lastName") String field,
			@RequestParam(value = "searchPattern", defaultValue = "") String searchPattern,
			@RequestParam(value = "prohibited", defaultValue = "false") boolean prohibited,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size)

	{
		GetRequestUser getRequestUser = new GetRequestUser();
		getRequestUser.setSearchField(searchField);
		getRequestUser.setField(field);
		getRequestUser.setProhibited(prohibited);
		getRequestUser.setSearchPattern(searchPattern);
		getRequestUser.setPage(page);
		getRequestUser.setSize(size);

		List<Customer> customers;
		try
		{
			customers = customerService.getCustomer(getRequestUser);
		}
		catch (Exception e)
		{
			customers = new ArrayList<>();
		}

		model.addAttribute("tableSwitch", getRequestUser.isProhibited());
		model.addAttribute("searchPattern", getRequestUser.getSearchPattern());
		model.addAttribute("customers", customers);
		return ("admins/consumer-list");
	}

	/*
	 * #######################################
	 * ######## Customer Prohibit ############
	 * #######################################
	 */

	@GetMapping("prohibit-customer")
	public String prohibitCustomer(
			Model model,
			@RequestParam(value = "customerId", required = true) int id)
	{

		Customer customer = customerService.getCustomerById(id);
		customer.getCustomerDetail().setProhibited(!customer.getCustomerDetail().isProhibited());
		customerService.saveCustomer(customer);

		return "redirect:/admins/customer-list?searchField=name&field=lastName&prohibited="
				+ !customer.getCustomerDetail().isProhibited();
	}

	/*
	 * #######################################
	 * ############ Stores List ##############
	 * #######################################
	 */

	@GetMapping("store-list")
	public String storeList(
			Model model,
			@RequestParam(value = "searchField", defaultValue = "storeAddress") String searchField,
			@RequestParam(value = "field", defaultValue = "storeName") String field,
			@RequestParam(value = "storeAddress", defaultValue = "") String storeAddress,
			@RequestParam(value = "searchPattern", defaultValue = "") String searchPattern,
			@RequestParam(value = "prohibited", defaultValue = "false") boolean prohibited,
			@RequestParam(value = "authorized", defaultValue = "true") boolean authorized,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size)

	{
		GetRequestStore getRequestStore = new GetRequestStore();
		getRequestStore.setSearchField(searchField);
		getRequestStore.setField(field);
		getRequestStore.setStoreAddress(storeAddress);
		getRequestStore.setSearchPattern(searchPattern);
		getRequestStore.setProhibited(prohibited);
		getRequestStore.setAuthorized(authorized);
		getRequestStore.setPage(page);
		getRequestStore.setSize(size);

		List<Store> stores;
		try
		{
			stores = storeService.getStore(getRequestStore);
		}
		catch (Exception e)
		{
			stores = new ArrayList<>();
		}

		model.addAttribute("tableSwitch", getRequestStore.isProhibited());
		model.addAttribute("searchPattern", getRequestStore.getSearchPattern());
		model.addAttribute("storeAddress", getRequestStore.getStoreAddress());

		model.addAttribute("stores", stores);
		return ("admins/store-list");
	}

	/*
	 * #######################################
	 * ############ RIDER Requests ##########
	 * #######################################
	 */
	@GetMapping("rider-requests")
	public String getRiderRequest(
			Model model,
			@RequestParam(value = "searchField", defaultValue = "") String searchField,
			@RequestParam(value = "field", defaultValue = "creationDate") String field,
			@RequestParam(value = "searchPattern", defaultValue = "") String searchPattern,
			@RequestParam(value = "authorized", defaultValue = "false") boolean authorized,
			@RequestParam(value = "prohibited", defaultValue = "false") boolean prohibited,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size)

	{
		log.info("hello");
		GetRequestUser getRequestUser = new GetRequestUser();
		getRequestUser.setSearchField(searchField);
		getRequestUser.setField(field);
		getRequestUser.setSearchPattern(searchPattern);
		getRequestUser.setAuthorized(authorized);
		getRequestUser.setPage(page);
		getRequestUser.setSize(size);

		List<Rider> riders;
		try
		{
			riders = riderService.getRiders(getRequestUser);
		}
		catch (Exception e)
		{
			riders = new ArrayList<>();
		}

		log.info(riders.size() + "");

		model.addAttribute("searchPattern", getRequestUser.getSearchPattern());
		model.addAttribute("riders", riders);
		return ("admins/rider-request");
	}

	/*
	 * #######################################
	 * ############ RIDER Verify ############
	 * #######################################
	 */

	@GetMapping("verify-rider")
	public String verifyRider(
			Model model,
			@RequestParam(value = "riderId", required = true) int id)
	{

		Rider rider = riderService.getRiderById(id);
		rider.getRiderDetail().setAuthorized(true);
		riderService.saveRider(rider);

		return "redirect:/admins/rider-requests?searchField=authorized&field=creationDate";
	}

	/*
	 * #######################################
	 * ############ RIDER Prohibit ############
	 * #######################################
	 */

	@GetMapping("prohibit-rider")
	public String prohibitRider(
			Model model,
			@RequestParam(value = "riderId", required = true) int id)
	{

		Rider rider = riderService.getRiderById(id);
		rider.getRiderDetail().setProhibited(!rider.getRiderDetail().isProhibited());
		riderService.saveRider(rider);

		return "redirect:/admins/rider-list?searchField=authorized&field=lastName"
				+ "&authorized=true&prohibited=" + !rider.getRiderDetail().isProhibited();
	}

	/*
	 * #######################################
	 * ############ RIDER List ##############
	 * #######################################
	 */

	@GetMapping("rider-list")
	public String riderList(
			Model model,
			@RequestParam(value = "searchField", defaultValue = "") String searchField,
			@RequestParam(value = "field", defaultValue = "creationDate") String field,
			@RequestParam(value = "searchPattern", defaultValue = "") String searchPattern,
			@RequestParam(value = "authorized", defaultValue = "true") boolean authorized,
			@RequestParam(value = "prohibited", defaultValue = "false") boolean prohibited,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "50") int size)

	{
		GetRequestUser getRequestUser = new GetRequestUser();
		getRequestUser.setSearchField(searchField);
		getRequestUser.setField(field);
		getRequestUser.setSearchPattern(searchPattern);
		getRequestUser.setAuthorized(authorized);
		getRequestUser.setProhibited(prohibited);
		getRequestUser.setPage(page);
		getRequestUser.setSize(size);

		List<Rider> riders;
		try
		{
			riders = riderService.getRiders(getRequestUser);
		}
		catch (Exception e)
		{
			riders = new ArrayList<>();
		}
		model.addAttribute("tableSwitch", getRequestUser.isProhibited());
		model.addAttribute("searchPattern", getRequestUser.getSearchPattern());

		model.addAttribute("riders", riders);
		return ("admins/rider-list");
	}
}
