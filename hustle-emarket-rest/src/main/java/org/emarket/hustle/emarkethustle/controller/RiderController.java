package org.emarket.hustle.emarkethustle.controller;

import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.entity.Rider;
import org.emarket.hustle.emarkethustle.service.DocumentService;
import org.emarket.hustle.emarkethustle.service.RiderService;
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
@RequestMapping("/riders")
public class RiderController
{
	Logger log = Logger.getLogger(RiderController.class.getName());

	@Autowired
	private RiderService riderService;

	@Autowired
	DocumentService documentConverter;

	@Autowired
	private ValidationService validationService;

	@GetMapping("/signup")
	public String riderSignup(Model model)
	{
		Rider rider = new Rider();
		model.addAttribute("rider", rider);
		return ("riders/signup");
	}

	@RequestMapping(path = "/save",
			method = RequestMethod.POST,
			consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String saveRider(
			@RequestParam("file") MultipartFile file,
			@ModelAttribute("rider") Rider rider)
	{

		rider = riderService.addRider(rider);
		String basePath = "documents/riders/" + rider.getId();
		rider.getRiderDetail().setDocuments(basePath);
		rider = riderService.updateRider(rider);

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
