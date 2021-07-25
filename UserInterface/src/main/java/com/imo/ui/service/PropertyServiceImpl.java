package com.imo.ui.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PropertyServiceImpl implements PropertyService{
	
	@Value("${host-url}")
	private String HOST;
	
	private String SERVICE = "consumer-service";
	
	@Autowired
	RestTemplate template;
	
	@Override
	public Map<String, List<String>> getProperties(String token) {
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Authorization",token);
		
		HttpEntity request = new HttpEntity(headers);
		System.out.println(this.HOST+this.SERVICE+"/bussiness/properties");
		ResponseEntity<Map> response = template.exchange(this.HOST+this.SERVICE+"/bussiness/properties",HttpMethod.GET,request,Map.class );
		
		if(response.getStatusCode()!=HttpStatus.OK) {
			
			return null;
			
		}
		
		return response.getBody();
	}

}
