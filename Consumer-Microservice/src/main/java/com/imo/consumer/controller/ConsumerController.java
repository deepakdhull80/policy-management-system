package com.imo.consumer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.imo.consumer.exception.AuthorizationException;
import com.imo.consumer.exception.ConsumerNotFoundException;
import com.imo.consumer.exception.NotEligibleException;
import com.imo.consumer.exception.ResponseHandlers;
import com.imo.consumer.exception.ServiceResponse;
import com.imo.consumer.model.BusinessDetails;
import com.imo.consumer.model.ConsumerDetails;
import com.imo.consumer.model.PropertyDetails;
import com.imo.consumer.service.ConsumerService;

@RestController
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;


	@PostMapping("/consumer")
	public ResponseEntity<ServiceResponse<ConsumerDetails>> createConsumer(@RequestBody ConsumerDetails consumerDetails)
			throws Exception {

		
		if (!consumerService.checkEligibility(consumerDetails)) {
			throw new NotEligibleException("Not Eligible");
		}
		
		
		ConsumerDetails consumer = consumerService.saveConsumer(consumerDetails);
		
		return new ResponseHandlers<ConsumerDetails>().defaultResponse(consumer, "Consumer Added", HttpStatus.OK);
	}

	@PutMapping("/consumers/{consumer_id}")
	public ResponseEntity<ServiceResponse<ConsumerDetails>> updateConsumer(@PathVariable Long consumer_id,
			@RequestBody ConsumerDetails consumerDetails) throws NotEligibleException, Exception {

		if (!consumerService.checkEligibility(consumerDetails)) {
			throw new NotEligibleException("Not Eligible");
		}
		ConsumerDetails consumer = consumerService.findConsumerById(consumer_id);

		consumer.setAgentname(consumerDetails.getAgentname());
		consumer.setDob(consumerDetails.getDob());
		consumer.setEmail(consumerDetails.getEmail());
		consumer.setName(consumerDetails.getName());
		consumer.setPandetails(consumerDetails.getPandetails());
		consumer.setPhone(consumerDetails.getPhone());

		List<BusinessDetails> businessDetails = consumer.getBusiness();
		List<BusinessDetails> business = consumerDetails.getBusiness();
		List<BusinessDetails> b2 = new ArrayList<>();
		for (int i = 0; i < business.size(); i++) {
			BusinessDetails b1 = businessDetails.get(i);
			BusinessDetails b = business.get(i);
			Long businessVal = consumerService.calBusinessValue(b.getBusinessturnover(), b.getCapitalinvested());
			b.setBusinessvalue(businessVal);
			b.setId(b1.getId());
			List<PropertyDetails> propertyDetails = b1.getProperty();
			List<PropertyDetails> property = b.getProperty();
			List<PropertyDetails> p2 = new ArrayList<>();
			for (int j = 0; j < property.size(); j++) {
				PropertyDetails p1 = propertyDetails.get(j);
				PropertyDetails p = property.get(j);
				Long propertyVal = consumerService.calPropertyValue(p.getCostoftheasset(), p.getSalvagevalue(),
						p.getUsefullifeoftheAsset());
				p.setPropertyvalue(propertyVal);
				p.setId(p1.getId());
				p2.add(p);
			}
			b2.add(b);
		}

		consumer.setBusiness(b2);
		ConsumerDetails con = consumerService.saveConsumer(consumer);

		return new ResponseHandlers<ConsumerDetails>().defaultResponse(con, "Consumer Updtaed", HttpStatus.OK);

	}

	@DeleteMapping("/consumers/{cid}")
	public ResponseEntity<ConsumerDetails> deleteConsumer(@PathVariable Long cid)
			throws ConsumerNotFoundException, AuthorizationException {

		ConsumerDetails con = consumerService.findConsumerById(cid);
		consumerService.deleteConsumer(con.getId());
		return new ResponseEntity<ConsumerDetails>(con, HttpStatus.OK);

	}

	@GetMapping("/getconsumers/{cid}")
	public ConsumerDetails viewConsumer(@PathVariable Long cid)
			throws ConsumerNotFoundException, AuthorizationException {

		ConsumerDetails con = consumerService.findConsumerById(cid);
		return con;

	}

	@GetMapping("/getallconsumers")
	public List<ConsumerDetails> viewAllConsumer(@RequestHeader("username") String username) throws AuthorizationException {

		List<ConsumerDetails> list = consumerService.findAllConsumers(username);
		return list;

	}

	@PostMapping(value = "createBusinessProperty/{cid}")
	public ResponseEntity<BusinessDetails> createBusinessProperty(@PathVariable Long cid,@RequestBody BusinessDetails businessDetails) throws ConsumerNotFoundException {
		BusinessDetails bs = consumerService.saveBusinessProperty(businessDetails,cid);
		return new ResponseEntity<BusinessDetails>(bs, HttpStatus.OK);

	}
	
	
	
}