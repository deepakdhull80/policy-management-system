package com.imo.ui.service;

import java.util.HashMap;
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
import com.imo.ui.modal.User;

@Service
public class LoginService {

	@Value("${host-url}")
	private String host;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	public String validate(User user) throws JsonProcessingException {

		String userJson = mapper.writeValueAsString(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> body = new HttpEntity<String>(userJson, headers);

		ResponseEntity<Object> token;
		try {

			token = restTemplate.postForEntity(this.host + "auth/login", body, Object.class);
		
		} catch (HttpClientErrorException e) {
			return null;
		}
		if (token.getStatusCode() == HttpStatus.OK) {
			HttpHeaders header = token.getHeaders();
			return header.get("Authorization").get(0);
		}
		return null;
	}

	public boolean isValid(String token) throws JsonProcessingException {
		
		token = token.substring(7);
		
		
		ResponseEntity<Boolean> response;
		try {
//			response = restTemplate.postForEntity(this.host + "auth/valid", req, boolean.class);
			response = restTemplate.getForEntity(this.host + "auth/valid/"+token, boolean.class);
			if(response.getStatusCode()==HttpStatus.OK  && response.getBody()) {
				return true;
			}
			
		} catch (HttpClientErrorException e) {
			System.out.println(e);
			return false;
		}
		
		
		return false;
	}
}
