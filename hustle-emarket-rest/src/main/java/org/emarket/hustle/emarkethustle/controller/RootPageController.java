package org.emarket.hustle.emarkethustle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootPageController
{
	@GetMapping("/")
	public String getIndex()
	{
		return ("index");
	}

	@GetMapping("/seller-signup")
	public String sellerSignup()
	{
		return ("seller-signup");
	}

}
