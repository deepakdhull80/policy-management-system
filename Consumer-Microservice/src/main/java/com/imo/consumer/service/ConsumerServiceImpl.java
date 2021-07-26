package com.imo.consumer.service;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imo.consumer.exception.ConsumerNotFoundException;
import com.imo.consumer.model.BusinessDetails;
import com.imo.consumer.model.BusinessMaster;
import com.imo.consumer.model.ConsumerDetails;
import com.imo.consumer.model.PropertyDetails;
import com.imo.consumer.model.PropertyMaster;
import com.imo.consumer.repository.BusinessMasterRepository;
import com.imo.consumer.repository.ConsumerRepository;
import com.imo.consumer.repository.PropertyMasterRepository;
import com.imo.consumer.repository.BusinessRepository;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	BusinessMasterRepository businessMasterRepository;

	@Autowired
	PropertyMasterRepository propertyMasterRepository;
	
	@Autowired
	private BusinessRepository businessPropertyRepo;

	@Override
	public ConsumerDetails saveConsumer(ConsumerDetails consumerDetails) {
		// TODO Auto-generated method stub
		List<BusinessDetails> business = consumerDetails.getBusiness();
		for (BusinessDetails b : business) {
			Long businessValue = calBusinessValue(b.getBusinessTurnOver(), b.getCapitalInvested());
			System.out.println(businessValue);
			b.setBusinessValue(businessValue);
			List<PropertyDetails> property = b.getProperty();
			for (PropertyDetails p : property) {
				Long propertyValue = calPropertyValue(p.getCostOfTheAsset(), p.getSalvageValue(),
						p.getUsefulLifeOfTheAsset());
				p.setPropertyValue(propertyValue);
			}
			b.setProperty(property);
		}
		consumerDetails.setBusiness(business);
		ConsumerDetails con = consumerRepository.save(consumerDetails);

		return con;
	}

	@Override
	public Long calBusinessValue(Long businessturnover, Long capitalinvested) {

		Double x_max = (double) businessturnover;
		Double x_min = (double) capitalinvested;
		Double x_ratio = x_max / x_min;
		Double Range_min = 0.00;
		Double Range_max = 10.00;
		Double range_diff = Range_max - Range_min;
		Double sat = ((x_ratio - x_min) / (x_max - x_min));
		Double businessvalue = (range_diff * sat);
		return Math.round(businessvalue);
	}

	@Override
	public void deleteConsumer(Long cid) {
		// TODO Auto-generated method stub
		consumerRepository.deleteById(cid);
	}

	@Override
	public ConsumerDetails findConsumerById(Long cid) throws ConsumerNotFoundException {
		// TODO Auto-generated method stub
		ConsumerDetails con = consumerRepository.findById(cid)
				.orElseThrow(() -> new ConsumerNotFoundException("Consumer not found"));
		return con;
	}

	@Override
	public List<ConsumerDetails> findAllConsumers(String agentName) {
		// TODO Auto-generated method stub
		
		List<ConsumerDetails> con = consumerRepository.findByAgentName(agentName);
		return con;
	}

	@Override
	public Boolean checkEligibility(ConsumerDetails consumerDetails) throws Exception {
		// TODO Auto-generated method stub
		Boolean check = false;

		List<BusinessDetails> businessDetails = consumerDetails.getBusiness();

		for (BusinessDetails b : businessDetails) {
			System.out.println(b.getBusinessCategory()+"  "+ b.getBusinessType());
			BusinessMaster businessMaster = businessMasterRepository
					.findByBusinessCategoryAndBusinessType(b.getBusinessCategory(), b.getBusinessType());
			if (businessMaster == null) {
				return false;
			}

			if (businessMaster!= null && (businessMaster.getTotalEmployees() <= b.getTotalEmployees()
					&& businessMaster.getBusinessAge() <= b.getBusinessAge())) {
				
				List<PropertyDetails> propertyDetails = b.getProperty();
				for (PropertyDetails p : propertyDetails) {
					PropertyMaster propertyMaster = propertyMasterRepository
							.findByBuildingTypeAndPropertyType(p.getBuildingType(), p.getPropertyType());
					if(propertyMaster == null && propertyMaster.getBuildingAge()> p.getBuildingAge()) {
						return false;
					}
					check = true;
				}
			}
		}
		return check;
	}

	@Override
	public Long calPropertyValue(Long costoftheasset, Long salvagevalue, Long usefullifeoftheAsset) {

		Double x_ratio = (double) ((costoftheasset - salvagevalue) / usefullifeoftheAsset);

		Double Range_min = 0.00;
		Double Range_max = 10.00;
		Double x_max = (double) (costoftheasset / usefullifeoftheAsset);

		Double x_min = (double) (salvagevalue / usefullifeoftheAsset);

		Double range_diff = (Range_max - Range_min);

		Double sat = ((x_ratio - x_min) / (x_max - x_min));

		Double propertyvalue = range_diff * sat;

		return (long) Math.abs(Math.round(propertyvalue));
	}
	
	@Override
	public BusinessDetails saveBusinessProperty(BusinessDetails businessDetails,Long cid) throws ConsumerNotFoundException {
		List<PropertyDetails> propertyDetails=businessDetails.getProperty();
		for(PropertyDetails propertObj:propertyDetails) {
			long propertyValue=calPropertyValue(propertObj.getCostOfTheAsset(), propertObj.getSalvageValue(),propertObj.getUsefulLifeOfTheAsset());
			propertObj.setPropertyValue(propertyValue);
		}
		businessDetails.setProperty(propertyDetails);
		
		
		
		/*
		 * it should be save through consumer repository, if not then this bussiness is not belong to any consumer
		 * 
		 * 
		 * */ 
		
		Optional<ConsumerDetails> conOptional =consumerRepository.findById(cid);
		if(conOptional.isEmpty()) {
			throw new ConsumerNotFoundException("Consumer Not Found");
		}
		conOptional.get().getBusiness().add(businessDetails);
		
		ConsumerDetails result =  consumerRepository.save(conOptional.get());
		
		return result.getBusiness().get(result.getBusiness().size()-1);
		
	}

}
