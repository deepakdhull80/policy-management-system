package com.imo.policy.service;

import java.util.List;

import com.imo.policy.exception.ConsumerNotFoundException;
import com.imo.policy.exception.PolicyNotFoundException;
import com.imo.policy.model.ConsumerDetails;
import com.imo.policy.model.ConsumerPolicy;
import com.imo.policy.model.PolicyMaster;

public interface PolicyService {

	Boolean checkPolicy(ConsumerDetails consumerDetails) throws Exception;

	ConsumerDetails savePolicy(ConsumerDetails consumerDetails);

//	PolicyMaster savePolicy(long cId, long bId);
	public PolicyMaster savePolicyMaster(PolicyMaster policyMaster);

	List<ConsumerPolicy> viewPolicy(Long cid, Long pid) throws ConsumerNotFoundException,PolicyNotFoundException;

	ConsumerPolicy issuePolicy(long cId, long bId);

}
