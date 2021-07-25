package com.imo.ui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imo.ui.exception.ConsumerNotFoundException;
import com.imo.ui.exception.UnAuthorizedException;
import com.imo.ui.modal.ConsumerDetails;
import com.imo.ui.modal.CreateConsumerRequest;

@Service
public interface ConsumerService {

	ConsumerDetails createConsumer(CreateConsumerRequest createConsumerRequest,String token) throws JsonProcessingException;

	List<ConsumerDetails> getAllConsumer(String user, String token);

	ConsumerDetails getConsumer(Long cid, String token) throws UnAuthorizedException,ConsumerNotFoundException;
	
}
