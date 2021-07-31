package com.imo.ui.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imo.ui.exception.UnAuthorizedException;
import com.imo.ui.modal.CreateBusinessRequest;
import com.imo.ui.service.BusinessService;
import com.imo.ui.service.ConsumerService;
import com.imo.ui.service.LoginService;
import com.imo.ui.service.PropertyService;

@Controller
public class BusinessController {

	
	@Autowired
	LoginService loginService;
	
	@Autowired
	PropertyService propertyService;

	@Autowired
	ConsumerService consumerService;
	
	@Autowired
	BusinessService businessSerivce;
	
	
	@GetMapping("/addBusiness/{cid}")
	public String addBusiness(@PathVariable long cid, HttpServletRequest request,ModelMap map) throws JsonProcessingException {
		
		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		if (token == null || user == null || !loginService.isValid(token)) {
			
			return "redirect:/login";

		}
		
		
		
		/* 
		 *  provide list data to view  
		 */
		Map<String,List<String>> response = propertyService.getProperties(token);
		
		map.addAttribute("cid",cid);
		map.addAttribute("agentId", user);
		map.addAttribute("bussinessType", response.get("bussinessType"));
		map.addAttribute("bussinessCategory", response.get("bussinessCategory"));
		map.addAttribute("propertyType", response.get("propertyType"));
		map.addAttribute("insuranceType", response.get("insuranceType"));
		map.addAttribute("buildingType", response.get("buildingType"));
		
		return "addBusiness";
		
	}
	
	@PostMapping("/add-business")
	public RedirectView postAddBusiness(@ModelAttribute CreateBusinessRequest businessDetails,HttpServletRequest request,RedirectAttributes attr) throws JsonProcessingException {
		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");
		
		
		RedirectView view =null;
		if (token == null || user == null || !loginService.isValid(token)) {
			view = new RedirectView("/login");
			attr.addFlashAttribute("msg", "session expired");
			
			return view;

		}
		
		view = new RedirectView("/dashboard");
		
		try {
		
		boolean res = businessSerivce.addBusiness(businessDetails,token);
		
		if(res) {
			attr.addFlashAttribute("success-msg", "Successfully added");
		}else {
			attr.addFlashAttribute("msg", "Not Eligible");
		}
		
		}catch(UnAuthorizedException e) {
			
			attr.addFlashAttribute("msg", "Invalid Token");
			
		}
				
		return view;
	}
	
	
	
}
