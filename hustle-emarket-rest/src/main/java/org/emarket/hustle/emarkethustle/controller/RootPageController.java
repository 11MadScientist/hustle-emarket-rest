package org.emarket.hustle.emarkethustle.controller;

import org.emarket.hustle.emarkethustle.entity.Admin;
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
	public String login(Model model)
	{
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return ("login");
	}

}
