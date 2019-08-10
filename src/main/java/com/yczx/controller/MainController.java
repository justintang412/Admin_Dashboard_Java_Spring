package com.yczx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	/**
	 * for top pages like login, error and others
	 * 
	 * @param session
	 * @param topPage
	 * @param model
	 * @return
	 */
	@GetMapping(path = "{topPage}")
	public String goTables(HttpSession session, @PathVariable String topPage, Model model) {
		return topPage;
	}

	/**
	 * for reference use
	 * 
	 * @param session
	 * @param sample
	 * @param model
	 * @return
	 */
	@GetMapping(path = "samples/{sample}")
	public String goSamples(HttpSession session, @PathVariable String sample, Model model) {
		return "samples/" + sample;
	}

	/**
	 * it's for official use
	 * 
	 * @param session
	 * @param sample
	 * @param model
	 * @return
	 */
	@GetMapping(path = "pages/{sample}")
	public String goPages(HttpSession session, @PathVariable String sample, Model model) {
		return "pages/" + sample;
	}
}
