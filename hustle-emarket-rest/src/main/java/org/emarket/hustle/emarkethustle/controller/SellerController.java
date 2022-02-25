package org.emarket.hustle.emarkethustle.controller;

import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.service.SellerService;
import org.emarket.hustle.emarkethustle.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sellers")
public class SellerController
{
	Logger log = Logger.getLogger(SellerController.class.getName());

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ValidationService validationService;

	@GetMapping("/signup")
	public String sellerSignup(Model model)
	{
		Seller seller = new Seller();
		model.addAttribute("seller", seller);
		return ("/sellers/signup");
	}

	@PostMapping("/save")
	public String saveSeller(@ModelAttribute("seller") Seller seller)
	{
		log.info("saving seller");
		sellerService.saveSeller(seller);

		return ("index");
	}

	@GetMapping("/validation")
	@ResponseBody
	public boolean validate(@RequestParam(required = false) String username, String email)
	{

		if(username != null)
		{
			return validationService.isUsernameNotTaken(username);
		}

		else if(email != null)
		{
			log.info(email);
			return validationService.isEmailNotTaken(email);
		}

		return false;

	}

}
