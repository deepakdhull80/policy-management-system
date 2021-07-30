package com.imo.ui.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imo.ui.exception.ConsumerNotFoundException;
import com.imo.ui.exception.UnAuthorizedException;
import com.imo.ui.modal.BusinessDetails;
import com.imo.ui.modal.ConsumerDetails;
import com.imo.ui.modal.ConsumerList;
import com.imo.ui.modal.CreateConsumerRequest;
import com.imo.ui.modal.PropertyDetails;

@Service
public class ConsumerServiceImpl implements ConsumerService{

	@Autowired
	RestTemplate template;
	
	@Autowired
	ObjectMapper mapper;
	
	
	@Value("${host-url}")
	String HOST;
	
	String SERVICE = "consumer-service/";
	
	@Override
	public ConsumerDetails createConsumer(CreateConsumerRequest co,String token) throws JsonProcessingException {
		
		ConsumerDetails consumerDetail = new ConsumerDetails();
		
		consumerDetail.setName(co.getConsumerName());
		consumerDetail.setAgentName(co.getAgentName());
		consumerDetail.setDob(co.getDateOfBirth());
		consumerDetail.setEmail(co.getEmail());
		consumerDetail.setPanDetails(co.getPanDetails());
		consumerDetail.setPhone(co.getPhone());
		
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
		
		List<BusinessDetails> listBusiness = new ArrayList<>();
		
		listBusiness.add(business);
		
		consumerDetail.setBusiness(listBusiness);
		
		
		/*
		 *  request to create customer
		 * 
		 */
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String con = mapper.writeValueAsString(consumerDetail);
		
		
		HttpEntity<ConsumerDetails> request = new HttpEntity<ConsumerDetails>(consumerDetail,headers);
		
		ResponseEntity<ConsumerDetails> response;
		try {

			response = template.postForEntity(this.HOST+this.SERVICE + "consumer", request, ConsumerDetails.class);
		
		} catch (HttpClientErrorException e) {
			return null;
		}
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}else if(response.getStatusCode() == HttpStatus.FORBIDDEN) {
			
			return null;
			
		}
		
		
		return null;
	}

	@Override
	public List<ConsumerDetails> getAllConsumer(String user, String token) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.add("username", user);
		
		HttpEntity<ConsumerDetails> request = new HttpEntity<ConsumerDetails>(headers);
		
		ResponseEntity<ConsumerDetails[]> response = template.exchange(this.HOST+this.SERVICE+"getallconsumers",HttpMethod.GET, request,ConsumerDetails[].class);
		
		return Arrays.asList(response.getBody());
	}

	@Override
	public ConsumerDetails getConsumer(Long cid, String token) throws UnAuthorizedException, ConsumerNotFoundException{
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		
		HttpEntity<ConsumerDetails> request = new HttpEntity<ConsumerDetails>(headers);
		
		ResponseEntity<ConsumerDetails> response = template.exchange(this.HOST+this.SERVICE+"/getconsumers/"+cid,HttpMethod.GET, request,ConsumerDetails.class);
		
		if(response.getStatusCode()==HttpStatus.OK) {
			
			
			return response.getBody();
			
		}else if(response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			throw new UnAuthorizedException("Unauthorized Access");
			
		}else if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
			throw new ConsumerNotFoundException("Consumer not exist id: "+cid);
		}
	
		return null;
	}

	@Override
	public ConsumerDetails updateConsumer(ConsumerDetails consumerDetails, String token) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		HttpEntity<ConsumerDetails> request = new HttpEntity<ConsumerDetails>(consumerDetails,headers);
		
		ResponseEntity<ConsumerDetails> response;
		try {
			response = template.exchange(this.HOST+this.SERVICE + "consumers/"+consumerDetails.getId(),HttpMethod.PUT ,request, ConsumerDetails.class);
			System.out.println(response.getBody());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			return null;
		}
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}else if(response.getStatusCode() == HttpStatus.FORBIDDEN) {
			
			return null;
			
		}
		
		
		return null;
	}

}
