package com.imo.consumer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imo.consumer.service.PropertyService;

@RestController
public class PropertyController {

	@Autowired
	PropertyService pService;
	
	@GetMapping("/bussiness/category")
	public List<String> getBussinessCategory(){
		return pService.getBussinessCategory();
	}
	
	@GetMapping("/bussiness/type")
	public List<String> getBussinessType(){
		return pService.getBussinessType();
	}
	
	@GetMapping("/insurance/type")
	public List<String> getInsuranceType(){
		return pService.getInsuranceType();
	}
	
	@GetMapping("/property/building/type")
	public List<String> getBuildingType(){
		return pService.getBuildingType();
	}
	
	@GetMapping("/property/type")
	public List<String> getPropertyType(){
		return pService.getPropertyType();
	}
	
	@GetMapping("/bussiness/properties")
	public Map<String, List<String>> getBussinessProperties(){
		
		Map<String,List<String>> properties = new HashMap<>();
		
		properties.put("bussinessCategory", pService.getBussinessCategory());
		properties.put("bussinessType", pService.getBussinessType());
		properties.put("insuranceType", pService.getInsuranceType());
		properties.put("buildingType", pService.getBuildingType());
		properties.put("propertyType", pService.getPropertyType());
		
		return properties;
	}
	
}
