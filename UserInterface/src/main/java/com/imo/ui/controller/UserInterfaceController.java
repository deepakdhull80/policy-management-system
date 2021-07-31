package com.imo.ui.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imo.ui.modal.User;
import com.imo.ui.service.LoginService;
import com.imo.ui.service.PropertyService;

@Controller
public class UserInterfaceController {

	@Autowired
	private LoginService loginService;

	@Autowired
	RestTemplate restTemplate;

	@Value("${host-url}")
	private String host;

	@GetMapping("/login")
	public String showLoginpage(HttpServletRequest request) throws JsonProcessingException {

		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		if (token != null && user != null && loginService.isValid(token)) {

			return "redirect:/dashboard";

		}

		return "login";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute("user") User user, ModelMap model, HttpServletRequest request)
			throws JsonProcessingException {

		String token = loginService.validate(user);
		if (token != null) {
			/*
			 * valid user having token
			 */
			token = "Bearer " + token;
			request.getSession().setAttribute("token", token);
			request.getSession().setAttribute("user", user.getUsername());
			return "redirect:/dashboard";
		}

		model.addAttribute("msg", "invalid username and password!!!");

		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {

		request.getSession().setAttribute("token", null);
		request.getSession().setAttribute("user", null);
		return "redirect:/login";

	}
	
	@Autowired
	PropertyService propertyService;
	
	@RequestMapping(value = {"/dashboard","/"})
	public String getHome(HttpServletRequest request,ModelMap map) throws JsonProcessingException {

		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		if (token == null || user == null || !loginService.isValid(token)) {
			
			return "redirect:/login";

		}
		
		/* 
		 *  provide list data to view  
		 */
		
		Map<String,List<String>> response = propertyService.getProperties(token);
		
		
		map.addAttribute("agentId", user);
		map.addAttribute("bussinessType", response.get("bussinessType"));
		map.addAttribute("bussinessCategory", response.get("bussinessCategory"));
		map.addAttribute("propertyType", response.get("propertyType"));
		map.addAttribute("insuranceType", response.get("insuranceType"));
		map.addAttribute("buildingType", response.get("buildingType"));
		
		return "index";

	}

	

}
