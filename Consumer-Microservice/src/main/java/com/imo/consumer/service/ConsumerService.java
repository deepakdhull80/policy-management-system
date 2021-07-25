package com.imo.consumer.service;

import java.util.List;

import com.imo.consumer.exception.ConsumerNotFoundException;
import com.imo.consumer.model.ConsumerDetails;
import com.imo.consumer.model.BusinessDetails;

public interface ConsumerService {
	
	ConsumerDetails saveConsumer(ConsumerDetails consumerDetails);

	void deleteConsumer(Long cid);

	ConsumerDetails findConsumerById(Long cid) throws ConsumerNotFoundException;

	List<ConsumerDetails> findAllConsumers(String username);

	Boolean checkEligibility(ConsumerDetails consumerDetails) throws Exception;
	
	public Long calBusinessValue(Long businessturnover, Long capitalinvested);
	
	public Long calPropertyValue(Long costoftheasset, Long salvagevalue, Long usefullifeoftheAsset);
	
	BusinessDetails saveBusinessProperty(BusinessDetails businessDetails, Long cid) throws ConsumerNotFoundException;
}
