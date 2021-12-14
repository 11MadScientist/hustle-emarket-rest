package org.emarket.hustle.hustleemarketrest.controller;

import java.nio.file.FileSystems;

import org.emarket.hustle.hustleemarketrest.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/images")
public class ImageController
{
	@Autowired
	ImageConverter imageConverter;

	@GetMapping(value = "/customers/{link}",
			produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte [] getCustomerProfile(@PathVariable String link)
	{
		String fs = FileSystems.getDefault().getSeparator();
		return (imageConverter.getImage(fs + "customers" + fs + link));
	}
}
