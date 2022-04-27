package org.emarket.hustle.emarkethustle.controller;

import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Seller;
import org.emarket.hustle.emarkethustle.service.DocumentService;
import org.emarket.hustle.emarkethustle.service.SellerService;
import org.emarket.hustle.emarkethustle.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/sellers")
public class SellerController
{
	Logger log = Logger.getLogger(SellerController.class.getName());

	@Autowired
	private SellerService sellerService;

	@Autowired
	DocumentService documentConverter;

	@Autowired
	private ValidationService validationService;

	@GetMapping("/signup")
	public String sellerSignup(Model model)
	{
		Seller seller = new Seller();
		MultipartFile file = null;

		model.addAttribute("file", file);
		model.addAttribute("seller", seller);
		return ("sellers/signup");
	}

	@RequestMapping(path = "/save",
			method = RequestMethod.POST,
			consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String saveSeller(
			@ModelAttribute("seller") Seller seller,
			@RequestParam("file") MultipartFile file)
	{
		seller = sellerService.addSeller(seller);
		String basePath = "sellers" + "/" + seller.getId();
		seller.getStore().setDocuments(basePath);
		seller = sellerService.updateSeller(seller);

		documentConverter.saveDocument(file, basePath);
		return ("redirect:/");
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
