package com.imo.policy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
				PolicyMaster policyMaster = policyMasterRepository
						.findByBusinessValueAndPropertyValue(b.getBusinessValue(), p.getPropertyValue());
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
	public ConsumerDetails issuePolicy(ConsumerDetails consumerDetails) {
		// TODO Auto-generated method stub
		List<BusinessDetails> businessDetails = consumerDetails.getBusiness();
		for (BusinessDetails b : businessDetails) {
			List<PropertyDetails> propertyDetails = b.getProperty();
			for (PropertyDetails p : propertyDetails) {
				PolicyMaster policyMaster = policyMasterRepository
						.findByBusinessValueAndPropertyValue(b.getBusinessValue(), p.getPropertyValue());
				String quotes = quotesclient.getQuotesForPolicy(b.getBusinessValue(), p.getPropertyValue(),
						p.getPropertyType());
				ConsumerPolicy consumerPolicy = new ConsumerPolicy();
				consumerPolicy.setAcceptedquote(quotes);
				consumerPolicy.setPid(policyMaster.getId());
				consumerPolicy.setAssured_sum(policyMaster.getAssured_sum());
				consumerPolicy.setBase_location(policyMaster.getBase_location());
				consumerPolicy.setBusinessValue(policyMaster.getBusinessValue());
				consumerPolicy.setConsumer_type(policyMaster.getConsumer_type());
				consumerPolicy.setProperty_type(policyMaster.getProperty_type());
				consumerPolicy.setPropertyValue(policyMaster.getPropertyValue());
				consumerPolicy.setTenure(policyMaster.getTenure());
				consumerPolicy.setType(policyMaster.getTenure());
				p.setConsumerPolicy(consumerPolicy);
			}
			b.setProperty(propertyDetails);
		}
		consumerDetails.setBusiness(businessDetails);
		ConsumerDetails con = consumerRepository.save(consumerDetails);
		return con;
	}

	@Override
	public PolicyMaster savePolicy(PolicyMaster policyMaster) {
		// TODO Auto-generated method stub
		PolicyMaster pol = policyMasterRepository.save(policyMaster);
		return pol;
	}

	@Override
	public List<ConsumerPolicy> viewPolicy(Long cid, Long pid)
			throws ConsumerNotFoundException, PolicyNotFoundException {
		// TODO Auto-generated method stub
		ConsumerDetails con = consumerRepository.findById(cid)
				.orElseThrow(() -> new ConsumerNotFoundException("Consumer Not Found"));
		List<ConsumerPolicy> list = new ArrayList<>();
		List<BusinessDetails> businessDetails = con.getBusiness();
		for (BusinessDetails b : businessDetails) {
			List<PropertyDetails> propertyDetails = b.getProperty();
			for (PropertyDetails p : propertyDetails) {
				ConsumerPolicy consumerPolicy = consumerPolicyRepository.findById(pid)
						.orElseThrow(() -> new PolicyNotFoundException("Policy Not Found"));
				list.add(consumerPolicy);
			}
		}
		return list;
	}

}
