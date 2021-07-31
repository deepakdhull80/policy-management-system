package com.imo.policy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.imo.policy.exception.AuthorizationException;
import com.imo.policy.exception.BusinessIdNotFoundException;
import com.imo.policy.exception.ConsumerNotFoundException;
import com.imo.policy.exception.IssuedPolicyException;
import com.imo.policy.exception.NotEligibleException;
import com.imo.policy.exception.PolicyNotFoundException;
import com.imo.policy.feign.AuthClient;
import com.imo.policy.feign.ConsumerClient;
import com.imo.policy.feign.QuotesClient;
import com.imo.policy.model.ConsumerDetails;
import com.imo.policy.model.ConsumerPolicy;
import com.imo.policy.model.PolicyMaster;
import com.imo.policy.request.ConsumerPolicyRequest;
import com.imo.policy.service.PolicyService;

@RestController
public class PolicyController {

	@Autowired
	ConsumerClient consumerClient;
	
	@Autowired
	QuotesClient quotesClient;
	
	@Autowired
	AuthClient auth;
	
	@Autowired
	PolicyService policyService;
	
	
	@PostMapping("/savePolicyMaster")
	public ResponseEntity<PolicyMaster> savePolicyMaster(@RequestBody PolicyMaster policyMaster) throws AuthorizationException{
		PolicyMaster pol = policyService.savePolicyMaster(policyMaster);
		return new ResponseEntity<PolicyMaster>(pol,HttpStatus.CREATED);
}
	
	@PostMapping("/issuePolicy")
	public ResponseEntity<ConsumerPolicy> issuePolicy(@RequestBody long uniqueId) throws AuthorizationException, PolicyNotFoundException, IssuedPolicyException{
			
			
			ConsumerPolicy con = policyService.issuePolicy(uniqueId);
			return new ResponseEntity<ConsumerPolicy>(con,HttpStatus.CREATED);
	}

	@PostMapping("/createPolicy")
	public ResponseEntity<ConsumerDetails> createPolicy(@RequestBody ConsumerPolicyRequest consumerPolicyRequest) throws PolicyNotFoundException,BusinessIdNotFoundException,Exception {

			Long cid = consumerPolicyRequest.getConsumerId();
			ConsumerDetails consumerDetails = consumerClient.viewConsumer(cid);
			if (!policyService.checkPolicy(consumerDetails)) {
				throw new NotEligibleException("Not Eligible");
			}
			ConsumerDetails con = policyService.savePolicy(consumerDetails,consumerPolicyRequest.getBusinessId());
			return new ResponseEntity<ConsumerDetails>(con,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewPolicy/{cid}/{pid}")
	public List<ConsumerPolicy> viewPolicyCon(@PathVariable Long cid,@PathVariable Long pid) throws ConsumerNotFoundException, AuthorizationException, PolicyNotFoundException {
			
		
			List<ConsumerPolicy> policyDetails = policyService.viewPolicy(cid,pid);
			if(policyDetails == null) {
				throw new PolicyNotFoundException("Notfound");
			}
			return policyDetails;

	}
	
	@GetMapping("/getConsumers/{cid}")
	public ConsumerDetails getConsumerDetails(@PathVariable Long cid)
			throws ConsumerNotFoundException, AuthorizationException {

		ConsumerDetails con = consumerClient.viewConsumer(cid);
		return con;

	}
	
	@GetMapping("/getQuotes/{businessValue}/{propertyValue}/{propertyType}")
	public String viewQuotes(@PathVariable Long businessValue, @PathVariable Long propertyValue, @PathVariable String propertyType) {
		
		String quotes = quotesClient.getQuotesForPolicy(businessValue, propertyValue, propertyType);
		
		return quotes;
	}
}
