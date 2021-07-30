package com.imo.ui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.imo.ui.exception.ConsumerNotFoundException;
import com.imo.ui.exception.PolicyNotFoundException;
import com.imo.ui.modal.ConsumerPolicy;
import com.imo.ui.modal.ConsumerPolicyRequest;

@Service
public interface PolicyService {

	List<ConsumerPolicy> getPolicy(long cid, long bid,String token) throws ConsumerNotFoundException;

	boolean createPolicy(ConsumerPolicyRequest detail, String token) throws ConsumerNotFoundException, PolicyNotFoundException;

	boolean issuePolicy(ConsumerPolicyRequest detail, String token) throws ConsumerNotFoundException, PolicyNotFoundException;
	
	
}

