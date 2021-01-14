package com.example.demo.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/content")
public class StaticController {

	@RequestMapping("/forwardPage")
	public String getForwardPage() {
		return "forward:/anotherPage.html";
	}

	@RequestMapping("/redirectPage")
	public String redirectPage() {
		return "redirect:/anotherPage.html";
	}

	@RequestMapping("/templatePage")
	public String templatePage(Model model) {
		model.addAttribute("date", new Date());
		return "currentDate";
	}

}
