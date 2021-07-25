package com.imo.consumer.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PropertyService {
		
	List<String> getBussinessCategory();
	
	List<String> getBussinessType();
	
	List<String> getInsuranceType();
	
	List<String> getPropertyType();
	
	List<String> getBuildingType();
	
}
