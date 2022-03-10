package org.emarket.hustle.emarkethustle.controller;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Logger;

import org.emarket.hustle.emarkethustle.BcryptSecurity;
import org.emarket.hustle.emarkethustle.entity.Rider;
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
	private ValidationService validationService;

	@Autowired
	private BcryptSecurity bcrypt;

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
			@ModelAttribute("seller") Rider rider)
	{

		rider.setPassword(bcrypt.encode(rider.getPassword()));

		rider = riderService.saveRider(rider);

		String fs = FileSystems.getDefault().getSeparator();

		Path basePath = FileSystems.getDefault()
				.getPath(".", "src", "main", "resources", "documents", "riders");

		String fileName = file.getOriginalFilename();

		String filePath = basePath.normalize().toAbsolutePath()
				+ fs + rider.getId();

		log.info(filePath);

		File directory = new File(filePath);

		if(!directory.exists())
		{
			directory.mkdir();
		}

		try
		{

			file.transferTo(new File(filePath + fs + fileName + ".pdf"));
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

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