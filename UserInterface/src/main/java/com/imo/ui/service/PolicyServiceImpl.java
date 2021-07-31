package com.imo.ui.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.imo.ui.exception.ConsumerNotFoundException;
import com.imo.ui.exception.PolicyNotFoundException;
import com.imo.ui.modal.BusinessDetails;
import com.imo.ui.modal.ConsumerDetails;
import com.imo.ui.modal.ConsumerPolicy;
import com.imo.ui.modal.ConsumerPolicyRequest;
import com.imo.ui.modal.PolicyMaster;

@Service
public class PolicyServiceImpl implements PolicyService{

	@Autowired
	private RestTemplate template;
	
	@Value("${host-url}")
	private String HOST;
	
	private String SERVICE = "policy-service/";
	
	
	@Override
	public List<ConsumerPolicy> getPolicy(long cid, long bid,String token) throws ConsumerNotFoundException {
		// TODO Auto-generated method stub
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<BusinessDetails> requestData = new HttpEntity<BusinessDetails>(headers);
		
		ResponseEntity<ConsumerPolicy[]> response=null;
		
		try {
		
		response = template.exchange(HOST+SERVICE+"/viewPolicy/"+cid+"/"+bid,HttpMethod.GET , requestData, ConsumerPolicy[].class);
		if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
			return null;
		}
		}catch(Exception e) {
			throw new ConsumerNotFoundException("Consumer Not Found");
		}
		
		return Arrays.asList(response.getBody());
	}


	@Override
	public boolean createPolicy(ConsumerPolicyRequest detail, String token) throws ConsumerNotFoundException, PolicyNotFoundException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<ConsumerPolicyRequest> requestData = new HttpEntity<ConsumerPolicyRequest>(detail,headers);
		
		ResponseEntity<ConsumerDetails> response=null;
		
		try {
			
			response = template.postForEntity(HOST+SERVICE+"/createPolicy", requestData, ConsumerDetails.class);
			if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
				return false;
			}else if(response.getStatusCode()==HttpStatus.NOT_ACCEPTABLE) {
				throw new PolicyNotFoundException("Master Policy Not Found");
			}
			}
		catch(PolicyNotFoundException e) {
			throw new PolicyNotFoundException(e.getMessage());
		}
		catch(Exception e) {
				e.printStackTrace();
				if(e.getMessage().startsWith("406")) {
					throw new PolicyNotFoundException("Master Policy Not Found");
				}
				throw new ConsumerNotFoundException("Consumer Not Found");
			}
		return true;
	}


	@Override
	public boolean issuePolicy(long uniqueId, String token) throws ConsumerNotFoundException, PolicyNotFoundException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Long> requestData = new HttpEntity<Long>(uniqueId,headers);
		
		ResponseEntity<ConsumerPolicy> response=null;
		
		try {
			
			response = template.postForEntity(HOST+SERVICE+"/issuePolicy", requestData, ConsumerPolicy.class);
			
			if(response.getStatusCode()==HttpStatus.NOT_FOUND) {
				return false;
			}else if(response.getStatusCode()==HttpStatus.NOT_ACCEPTABLE) {
				throw new PolicyNotFoundException("Policy Not Found");
			}
			}
		catch(PolicyNotFoundException e) {
			throw new PolicyNotFoundException(e.getMessage());
		}
		catch(Exception e) {
				if(e.getMessage().startsWith("406")) {
					throw new PolicyNotFoundException("Policy Not Found");
				}
				throw new ConsumerNotFoundException("Consumer Not Found");
			}
		return true;
	}


	@Override
	public boolean addPolicyMaster(PolicyMaster policyMaster, String token) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<PolicyMaster> requestData = new HttpEntity<PolicyMaster>(policyMaster,headers);
		
		ResponseEntity<PolicyMaster> response=null;
		
		try {
			
			response = template.postForEntity(HOST+SERVICE+"/savePolicyMaster", requestData, PolicyMaster.class);
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
