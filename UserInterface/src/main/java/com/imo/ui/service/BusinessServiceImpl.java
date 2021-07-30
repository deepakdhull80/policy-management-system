package com.imo.ui.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imo.ui.exception.UnAuthorizedException;
import com.imo.ui.modal.BusinessDetails;
import com.imo.ui.modal.ConsumerDetails;
import com.imo.ui.modal.CreateBusinessRequest;
import com.imo.ui.modal.PropertyDetails;

@Service
public class BusinessServiceImpl implements BusinessService{

	@Autowired
	private RestTemplate template;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	@Value("${host-url}")
	private String HOST;
	
	private String SERVICE = "consumer-service/";
	
	@Override
	public boolean addBusiness(CreateBusinessRequest co,String token) throws UnAuthorizedException{
		
		long cid = co.getCid();
		
		BusinessDetails business = new BusinessDetails();
		business.setBusinessAge(co.getBusinessAge());
		business.setBusinessCategory(co.getBusinessCategory());
		business.setBusinessTurnOver(co.getBusinessTurnover());
		business.setBusinessType(co.getBusinessType());
		business.setCapitalInvested(co.getCapitalInvested());
		business.setTotalEmployees(co.getTotalEmployee());
		
		PropertyDetails propertyDetails = new PropertyDetails();
		propertyDetails.setBuildingAge(co.getBuildingAge());
		propertyDetails.setBuildingSqft(co.getBuildingsqft());
		propertyDetails.setBuildingStoreys(co.getBuildingStoreys());
		propertyDetails.setBuildingType(co.getBuildingType());
		propertyDetails.setPropertyType(co.getPropertyType());
		propertyDetails.setCostOfTheAsset(co.getCostOfTheAsset());
		propertyDetails.setUsefulLifeOfTheAsset(co.getLifeOfAsset());
		propertyDetails.setSalvageValue(co.getSalvageValue());
		
		List<PropertyDetails > listProps = new ArrayList<>();
		listProps.add(propertyDetails);
		business.setProperty(listProps);
		
		// request
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<BusinessDetails> requestData = new HttpEntity<BusinessDetails>(business,headers);
		
		ResponseEntity<BusinessDetails> response;
		try {

			response = template.postForEntity(this.HOST+this.SERVICE + "createBusinessProperty/"+cid, requestData, BusinessDetails.class);
			
			System.out.println(response.getBody());
			
		} catch (HttpClientErrorException e) {
			return false;
		}
		if (response.getStatusCode() == HttpStatus.OK) {
			return true;
		}else if(response.getStatusCode() == HttpStatus.FORBIDDEN) {
			
			throw new UnAuthorizedException("Invalid Token");
			
		}
		
		return false;
	}

	
	
}
