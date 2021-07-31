package com.imo.ui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.imo.ui.modal.QuotesMaster;

@Service
public class QuoteServiceImpl implements QuoteService{

	
	@Autowired
	private RestTemplate template;
	
	@Value("${host-url}")
	private String HOST;
	
	private String SERVICE = "quotes-service/";
	
	
	@Override
	public boolean addQuoteMaster(QuotesMaster quoteMaster, String token) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<QuotesMaster> requestData = new HttpEntity<QuotesMaster>(quoteMaster,headers);
		
		ResponseEntity<Boolean> response=null;
		
		try {
			
			response = template.postForEntity(HOST+SERVICE+"/addQuote", requestData, Boolean.class);
			if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
				return false;
			}
		}
		
		catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		
		System.out.println(response.hasBody());
		return true;
	}

}
