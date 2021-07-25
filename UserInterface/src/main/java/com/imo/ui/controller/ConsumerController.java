package com.imo.ui.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imo.ui.exception.ConsumerNotFoundException;
import com.imo.ui.exception.UnAuthorizedException;
import com.imo.ui.modal.ConsumerDetails;
import com.imo.ui.modal.CreateConsumerRequest;
import com.imo.ui.service.ConsumerService;
import com.imo.ui.service.LoginService;
import com.imo.ui.service.PropertyService;

@Controller
public class ConsumerController {

	@Autowired
	LoginService loginService;

	@Autowired
	PropertyService propertyService;

	@Autowired
	ConsumerService consumerService;

	@GetMapping("/consumerDetails")
	public String consumerDetails(HttpServletRequest request, ModelMap map) throws JsonProcessingException {

		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		if (token == null || user == null || !loginService.isValid(token)) {

			return "redirect:/login";

		}

		Map<String, List<String>> response = propertyService.getProperties(token);

		map.addAttribute("agentId", user);
		map.addAttribute("bussinessType", response.get("bussinessType"));
		map.addAttribute("bussinessCategory", response.get("bussinessCategory"));
		map.addAttribute("propertyType", response.get("propertyType"));
		map.addAttribute("insuranceType", response.get("insuranceType"));
		map.addAttribute("buildingType", response.get("buildingType"));

		
		
		/*
		 * 
		 * all consumer details
		 * 
		 */
		
		List<ConsumerDetails> conList =  consumerService.getAllConsumer(user,token);
		
		map.addAttribute("consumersList", conList);
		
		return "consumerDetails";
	}

	@PostMapping("/add-consumer")
	public RedirectView createConsumer(@ModelAttribute CreateConsumerRequest consumerDetails,
			HttpServletRequest request, RedirectAttributes attr) throws JsonProcessingException {

		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		RedirectView view;

		if (token == null || user == null || !loginService.isValid(token)) {
			view = new RedirectView("/login", true);

			attr.addFlashAttribute("msg", "Session Expired");

			return view;

		}

		ConsumerDetails consumer = consumerService.createConsumer(consumerDetails, token);
		view = new RedirectView("/dashboard", true);
		if (consumer == null) {

			attr.addFlashAttribute("msg", "Not Eligible");
			return view;
		}
		
		attr.addFlashAttribute("msg", "Added Successfully");
		
		return view;
	}

	@GetMapping("/viewConsumer/{cid}")
	public String viewConsumerDetails(@PathVariable Long cid,HttpServletRequest request, ModelMap map) throws JsonProcessingException, UnAuthorizedException, ConsumerNotFoundException {
		
		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		if (token == null || user == null || !loginService.isValid(token)) {

			return "redirect:/login";

		}
		
		ConsumerDetails consumer = consumerService.getConsumer(cid, token);
		
		if(consumer==null) {
			
			return "redirect:/consumerDetails";
			
		}
		
		Map<String, List<String>> response = propertyService.getProperties(token);

		map.addAttribute("agentId", user);
		map.addAttribute("bussinessType", response.get("bussinessType"));
		map.addAttribute("bussinessCategory", response.get("bussinessCategory"));
		map.addAttribute("propertyType", response.get("propertyType"));
		map.addAttribute("insuranceType", response.get("insuranceType"));
		map.addAttribute("buildingType", response.get("buildingType"));
		
		map.addAttribute("consumer", consumer);
		
		return "viewConsumer";
	}
	
	@GetMapping("/updateConsumer/{cid}")
	public String updateConsumer(@PathVariable Long cid,HttpServletRequest request, ModelMap map) throws UnAuthorizedException, ConsumerNotFoundException, JsonProcessingException {
		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		if (token == null || user == null || !loginService.isValid(token)) {

			return "redirect:/login";

		}
		
		ConsumerDetails consumer = consumerService.getConsumer(cid, token);
		
		if(consumer==null) {
			
			return "redirect:/consumerDetails";
			
		}
		
		Map<String, List<String>> response = propertyService.getProperties(token);

		map.addAttribute("agentId", user);
		map.addAttribute("bussinessType", response.get("bussinessType"));
		map.addAttribute("bussinessCategory", response.get("bussinessCategory"));
		map.addAttribute("propertyType", response.get("propertyType"));
		map.addAttribute("insuranceType", response.get("insuranceType"));
		map.addAttribute("buildingType", response.get("buildingType"));
		
		map.addAttribute("consumer", consumer);
		
		return null;
	}
	

}
