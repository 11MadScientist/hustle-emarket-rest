package org.emarket.hustle.emarkethustle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootPageController
{
	@GetMapping("/")
	public String getIndex()
	{
		return ("index");
	}

	@GetMapping("/login")
	public String login()
	{
		return ("login");
	}

	@GetMapping("/logout")
	public String logout()
	{
		return ("logout");
	}

	@GetMapping("/template")
	public String template(Model model)
	{
		model.addAttribute("name", "novert");
		model.addAttribute("body", "hello");
		return ("email-template");
	}

}
