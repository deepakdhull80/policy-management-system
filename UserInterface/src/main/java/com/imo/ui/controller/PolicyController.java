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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imo.ui.exception.ConsumerNotFoundException;
import com.imo.ui.exception.PolicyNotFoundException;
import com.imo.ui.modal.ConsumerPolicy;
import com.imo.ui.modal.ConsumerPolicyRequest;
import com.imo.ui.modal.PolicyMaster;
import com.imo.ui.modal.PolicyRequest;
import com.imo.ui.service.LoginService;
import com.imo.ui.service.PolicyService;
import com.imo.ui.service.PropertyService;

@Controller
public class PolicyController {

	@Autowired
	LoginService loginService;

	@Autowired
	PropertyService propertyService;

	@Autowired
	PolicyService policyService;

	
	/*
	 * 
	 * Policy View Page
	 * 
	 */
	
	
	@GetMapping("/policyDetails")
	public String policyDetails(HttpServletRequest request, ModelMap map) throws JsonProcessingException {

		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		if (token == null || user == null || !loginService.isValid(token)) {

			return "redirect:/login";

		}

		// logic part

		/*
		 * provide list data to view
		 */
		Map<String, List<String>> response = propertyService.getProperties(token);
		map.addAttribute("agentId", user);
		map.addAttribute("bussinessType", response.get("bussinessType"));
		map.addAttribute("bussinessCategory", response.get("bussinessCategory"));
		map.addAttribute("propertyType", response.get("propertyType"));
		map.addAttribute("insuranceType", response.get("insuranceType"));
		map.addAttribute("buildingType", response.get("buildingType"));

		return "policyDetails";
	}

	
	
	/*
	 * 
	 * View Policy get Request
	 * 
	 */
	
	
	@GetMapping("/viewPolicy/{cid}/{bid}")
	public String viewPolicyDetails(@PathVariable long cid,@PathVariable long bid,HttpServletRequest request,RedirectAttributes attr, ModelMap map) throws JsonProcessingException {

		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		if (token == null || user == null || !loginService.isValid(token)) {

			return "redirect:/login";

		}

		// logic part

		/*
		 * provide list data to view
		 */
		Map<String, List<String>> response = propertyService.getProperties(token);
		map.addAttribute("agentId", user);
		map.addAttribute("bussinessType", response.get("bussinessType"));
		map.addAttribute("bussinessCategory", response.get("bussinessCategory"));
		map.addAttribute("propertyType", response.get("propertyType"));
		map.addAttribute("insuranceType", response.get("insuranceType"));
		map.addAttribute("buildingType", response.get("buildingType"));

		
		List<ConsumerPolicy> policyList=null;
		try {
			policyList = policyService.getPolicy(cid, bid, token);
		} catch (ConsumerNotFoundException e) {
			return "redirect:/policyDetails";
		}
		
		if(policyList !=null && policyList.isEmpty()) {
			return "redirect:/policyDetails";
		}
		
		map.addAttribute("policyList", policyList);
		
		
		return "viewPolicy";
	}

	
	
	
	/*
	 * 
	 * 
	 * View Policy request
	 * 
	 */
	
	
	
	
	@PostMapping("/view-policy")
	public RedirectView viewPolicyForConsumer(@ModelAttribute PolicyRequest detail, RedirectAttributes attr,
			HttpServletRequest request) throws JsonProcessingException {

		String token = (String) request.getSession().getAttribute("token");

		String user = (String) request.getSession().getAttribute("user");

		RedirectView view;

		if (token == null || user == null || !loginService.isValid(token)) {

			view = new RedirectView("/login");
			attr.addFlashAttribute("msg", "Token Expired");
			return view;

		}

		// request policies
		List<ConsumerPolicy> consumerPolicyList = null;
		try {
			consumerPolicyList = policyService.getPolicy(detail.getCid(), detail.getBid(), token);

		} catch (ConsumerNotFoundException e) {
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Consumer Not Found or not created any Policy");
			return view;
		}

		// no policies registered
		
		view = new RedirectView("/viewPolicy/"+detail.getCid()+"/"+detail.getBid());
		
		System.out.println(consumerPolicyList);
		
		attr.addFlashAttribute("cid", detail.getCid());
		attr.addFlashAttribute("bid", detail.getBid());
		attr.addFlashAttribute("consumerPolicy", consumerPolicyList);
		request.setAttribute("consumerPolicy", consumerPolicyList);
		return view;
	}

	
	/*
	 * 
	 * Create new policy
	 * 
	 * 
	 */
	
	@PostMapping("/create-policy")
	public RedirectView createPolicyForConsumer(@ModelAttribute ConsumerPolicyRequest detail, RedirectAttributes attr,
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
		try {
			response = policyService.createPolicy(detail,token);
		} catch (ConsumerNotFoundException e) {
			e.printStackTrace();
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Consumer Not Found");
			return view;
		} catch (PolicyNotFoundException e) {
			e.printStackTrace();
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Policy Not Found in Policy Master");
			return view;
		}
		if(response) {
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Policy created consumer id :"+detail.getConsumerId()+" and business id :"+detail.getBusinessId());
		}else {
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Invalid Business Id");
		}

		return view;
	}

	/*
	 * 
	 * Issue Policy
	 * 
	 * 
	 */
	
	
	
	@PostMapping("/issue-policy")
	public RedirectView issuePolicyForConsumer(@ModelAttribute ConsumerPolicyRequest detail, RedirectAttributes attr,
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
		try {
			response = policyService.issuePolicy(detail,token);
		} catch (ConsumerNotFoundException e) {
			e.printStackTrace();
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Consumer Not Found");
			return view;
		} catch (PolicyNotFoundException e) {
			e.printStackTrace();
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Policy Not Found in Policy Master");
			return view;
		}
		if(response) {
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Policy Issued consumer id :"+detail.getConsumerId()+" and business id :"+detail.getBusinessId());
		}else {
			view = new RedirectView("/policyDetails");
			attr.addFlashAttribute("msg", "Invalid Business Id");
		}

		return view;
	}
	
	
	/*
	 * Add Policy Master
	 * 
	 */
	
	@PostMapping("/add-policy-master")
	public RedirectView createPolicyMaster(@ModelAttribute PolicyMaster policyMaster, RedirectAttributes attr,
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
		response = policyService.addPolicyMaster(policyMaster,token);
		
		if(response) {
			view = new RedirectView("/");
			attr.addFlashAttribute("msg", "Policy Registered");
		}else {
			view = new RedirectView("/");
			attr.addFlashAttribute("msg", "Unable To Register Policy");
		}

		return view;
	}
	
	
	
	
}
