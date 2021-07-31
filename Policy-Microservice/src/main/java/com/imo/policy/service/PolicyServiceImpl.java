package com.imo.policy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imo.policy.exception.BusinessIdNotFoundException;
import com.imo.policy.exception.ConsumerNotFoundException;
import com.imo.policy.exception.PolicyNotFoundException;
import com.imo.policy.feign.QuotesClient;
import com.imo.policy.model.BusinessDetails;
import com.imo.policy.model.ConsumerDetails;
import com.imo.policy.model.ConsumerPolicy;
import com.imo.policy.model.PolicyMaster;
import com.imo.policy.model.PropertyDetails;
import com.imo.policy.repository.ConsumerPolicyRepository;
import com.imo.policy.repository.ConsumerRepository;
import com.imo.policy.repository.PolicyMasterRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyMasterRepository policyMasterRepository;

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	ConsumerPolicyRepository consumerPolicyRepository;

	@Autowired
	QuotesClient quotesclient;

	@Override
	public Boolean checkPolicy(ConsumerDetails consumerDetails) throws Exception {
		// TODO Auto-generated method stub
		boolean check = false;
		List<BusinessDetails> businessDetails = consumerDetails.getBusiness();
		for (BusinessDetails b : businessDetails) {
			List<PropertyDetails> propertyDetails = b.getProperty();
			for (PropertyDetails p : propertyDetails) {
				PolicyMaster policyMaster = policyMasterRepository.findByBusinessValueAndPropertyValueAndPropertyType(
						b.getBusinessValue(), p.getPropertyValue(), p.getPropertyType());
				String quotes = quotesclient.getQuotesForPolicy(b.getBusinessValue(), p.getPropertyValue(),
						p.getPropertyType());
				if (policyMaster == null || quotes == null) {
					check = false;
				}
				check = true;
			}
		}
		return check;
	}

	@Override
	public ConsumerDetails savePolicy(ConsumerDetails consumerDetails,Long bid) throws PolicyNotFoundException, BusinessIdNotFoundException {
		// TODO Auto-generated method stub
		List<BusinessDetails> businessDetails = consumerDetails.getBusiness();
		
		long cId = consumerDetails.getId();
		boolean flag = false;
		for (BusinessDetails b : businessDetails) {
			
			if(b.getId() == bid && flag==false) { 
				flag=true;
				List<PropertyDetails> propertyDetails = b.getProperty();
				
				for (PropertyDetails p : propertyDetails) {
					PolicyMaster policyMaster = policyMasterRepository.findByBusinessValueAndPropertyValueAndPropertyType(
							b.getBusinessValue(), p.getPropertyValue(), p.getPropertyType());
					
					if(policyMaster==null) {
						throw new PolicyNotFoundException("Not Valid Policy");
					}
					
					String quotes = quotesclient.getQuotesForPolicy(b.getBusinessValue(), p.getPropertyValue(),
							p.getPropertyType());
	
					long bId = b.getId();
	
					ConsumerPolicy consumerPolicy = new ConsumerPolicy();
					consumerPolicy.setAcceptedQuote(quotes);
					consumerPolicy.setPid(policyMaster.getId());
					consumerPolicy.setAssuredSum(policyMaster.getAssuredSum());
					consumerPolicy.setBaseLocation(policyMaster.getBaseLocation());
					consumerPolicy.setBusinessValue(policyMaster.getBusinessValue());
					consumerPolicy.setConsumerType(policyMaster.getConsumerType());
					consumerPolicy.setPropertyType(policyMaster.getPropertyType());
					consumerPolicy.setPropertyValue(policyMaster.getPropertyValue());
					consumerPolicy.setTenure(policyMaster.getTenure());
					consumerPolicy.setType(policyMaster.getTenure());
					consumerPolicy.setBusinessId(bId);
					consumerPolicy.setConsumerId(cId);
					consumerPolicy.setStatus("Initiated");
					p.setConsumerPolicy(consumerPolicy);
				}
				b.setProperty(propertyDetails);
			}
		}
		
		if(!flag) {
			
			throw new BusinessIdNotFoundException("Business Id Not Found");
			
		}
		
		consumerDetails.setBusiness(businessDetails);
		ConsumerDetails con = consumerRepository.save(consumerDetails);
		return con;
	}

	@Override
	public ConsumerPolicy issuePolicy(long uniqueId) throws PolicyNotFoundException {
		// TODO Auto-generated method stub
		ConsumerPolicy consumerPolicy = consumerPolicyRepository.findById(uniqueId).orElseThrow(()-> new PolicyNotFoundException("policy not found"));
		consumerPolicy.setStatus("Issued");
		consumerPolicyRepository.save(consumerPolicy);
		return consumerPolicy;
	}

	@Override
	public List<ConsumerPolicy> viewPolicy(Long cid, Long bid)
			throws ConsumerNotFoundException, PolicyNotFoundException {

		ConsumerDetails con = consumerRepository.findById(cid)
				.orElseThrow(() -> new ConsumerNotFoundException("Consumer Not Found"));

		List<ConsumerPolicy> list = new ArrayList<>();
		List<BusinessDetails> businessDetails = con.getBusiness();

		for (BusinessDetails b : businessDetails) {
			List<PropertyDetails> propertyDetails = b.getProperty();
			for (PropertyDetails p : propertyDetails) {
				List<ConsumerPolicy> consumerPolicy = consumerPolicyRepository.findByBusinessId(bid);
				if(consumerPolicy.isEmpty()) {
					throw new PolicyNotFoundException("Policy Not Found.");
				}
				list.addAll(consumerPolicy);
			}
		}
		return list;
	}

	@Override
	public PolicyMaster savePolicyMaster(PolicyMaster policyMaster) {
		// TODO Auto-generated method stub
		PolicyMaster pol = policyMasterRepository.save(policyMaster);
		return pol;
	}

}
