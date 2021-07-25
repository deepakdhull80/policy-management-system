package com.imo.ui.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imo.ui.modal.PolicyMaster;
import com.imo.ui.service.LoginService;
import com.imo.ui.service.PropertyService;

@Controller
public class PolicyController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	PropertyService propertyService;
	
	@GetMapping("/policyDetails")
	public String policyDetails(HttpServletRequest request,ModelMap map) throws JsonProcessingException {
		
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

		
		return "policyDetails";
	}

	@GetMapping("/viewPolicy")
	public String viewPolicyDetails() {
		return "viewPolicy";
	}

	@PostMapping("/createPolicy")
	public ModelAndView createPolicy(@ModelAttribute("createPolicy") PolicyMaster policyMaster, BindingResult result,
			ModelAndView modelAndView) {

		String restApi = "localhost:8200/createPolicy";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("property_type", "a");
		jsonObject.put("consumer_type", "b");
		jsonObject.put("assured_sum", "c");
		jsonObject.put("tenure", "d");
		jsonObject.put("businessValue", 1);
		jsonObject.put("propertyValue", 2);
		jsonObject.put("base_location", "e");
		jsonObject.put("type", "f");

		HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), requestHeaders);

//		String response = restTemplate.postForObject(restApi, request, String.class);

		modelAndView.setViewName("index");

		return modelAndView;
	}
	
}
