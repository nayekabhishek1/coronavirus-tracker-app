package com.nayek.coronavirustrackerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nayek.coronavirustrackerapp.service.CoronaAllDataService;

@Controller
public class CoronaHomeController {

	@Autowired
	CoronaAllDataService coronaAllDataService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("caselist", coronaAllDataService.getMainList());
		return "index";
	}
}
