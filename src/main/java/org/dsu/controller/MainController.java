package org.dsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping(method = RequestMethod.GET)
	public String start(Model model) {
		return "index";
	}
	
	@RequestMapping(value = {"/forbidd"},  method = RequestMethod.GET)
	public String forbidd(Model model) {
		return "forbidd";
	}

}