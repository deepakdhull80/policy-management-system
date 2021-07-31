package com.imo.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imo.ui.modal.PolicyMaster;
import com.imo.ui.modal.QuotesMaster;
import com.imo.ui.service.LoginService;
import com.imo.ui.service.QuoteService;

@Controller
public class QuoteController {
	
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	QuoteService quoteService;
	
	/*
	 * Add Policy Master
	 * 
	 */
	
	@PostMapping("/add-quote-master")
	public RedirectView createQuoteMaster(@ModelAttribute QuotesMaster quoteMaster, RedirectAttributes attr,
			HttpServletRequest request) throws JsonProcessingException {

		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		RedirectView view=null;

		if (token == null || user == null || !loginService.isValid(token)) {

			view = new RedirectView("/login");
			attr.addFlashAttribute("msg", "Token Expired");
			return view;

		}
		
		boolean response=true;
		response = quoteService.addQuoteMaster(quoteMaster,token);
		
		if(response) {
			view = new RedirectView("/");
			attr.addFlashAttribute("success-msg", "Quote Created");
		}else {
			view = new RedirectView("/");
			attr.addFlashAttribute("msg", "Unable To Create Quote");
		}

		return view;
	}
	
}
