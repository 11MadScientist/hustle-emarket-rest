package org.emarket.hustle.emarkethustle.controller;

import java.util.List;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Store;
import org.emarket.hustle.emarkethustle.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
public class AdminController
{
	Logger log = Logger.getLogger(AdminController.class.getName());

	@Autowired
	StoreService storeService;

	@GetMapping("/dashboard")
	public String adminPage(Model model)
	{
		List<Store> stores = storeService.getStoreByOverallStockSold();
		model.addAttribute("stores", stores);
		log.info(stores.get(0).getOverallStockSold() + "");
		log.info(stores.get(1).getOverallStockSold() + "");
		return ("admins/dashboard");
	}

}
