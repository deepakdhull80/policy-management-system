package com.imo.consumer;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.imo.consumer.model.BusinessDetails;
import com.imo.consumer.model.BusinessMaster;
import com.imo.consumer.model.ConsumerDetails;
import com.imo.consumer.model.PropertyDetails;
import com.imo.consumer.model.PropertyMaster;
import com.imo.consumer.repository.BusinessMasterRepository;
import com.imo.consumer.repository.ConsumerRepository;
import com.imo.consumer.repository.PropertyMasterRepository;
import com.imo.consumer.service.ConsumerService;
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ConsumerMicroserviceApplication implements ApplicationRunner{

	@Autowired
	BusinessMasterRepository busMasterRepository;
	
	@Autowired
	PropertyMasterRepository prRepository;
	
	@Autowired
	ConsumerRepository crepo;
	
	@Autowired
	ConsumerService cService;
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerMicroserviceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//id businesscategory businesstype totalemployees businessage 
		
		BusinessMaster businessMaster = new BusinessMaster(1L,"Permissible","Real Estate",45L,2L);
		
		busMasterRepository.save(businessMaster);
		
		businessMaster = new BusinessMaster(2L,"Permissible","Institute",20L,2L);
		
		busMasterRepository.save(businessMaster);
		
		//id insurancetype propertytype buildingtype buildingage
		
		
		PropertyMaster propertyMaster = new PropertyMaster(1L,"Property", "PRIVATE", "Institute", 12L);
		
		prRepository.save(propertyMaster);
		
		propertyMaster = new PropertyMaster(2L,"Property", "PRIVATE", "Property", 5L);
		
		prRepository.save(propertyMaster);
		
		
		// create consumer 
		
		ConsumerDetails consumer = new ConsumerDetails();
		
		consumer.setName("deepak");
		consumer.setAgentName("agent1");
		consumer.setDob("17-10-2000");
		consumer.setEmail("xyz80@gmail.com");
		consumer.setPanDetails("9876543211");
		consumer.setPhone("9876543210");
		
		BusinessDetails bu = new BusinessDetails();
		
		bu.setBusinessAge(21L);
		bu.setBusinessCategory("Permissible");
		bu.setBusinessTurnOver(12133333L);
		bu.setBusinessType("Institute");
		bu.setCapitalInvested(3133333L);
		bu.setTotalEmployees(3000L);
		List<PropertyDetails> lp = new ArrayList<>();
		
		PropertyDetails p = new PropertyDetails();
		p.setBuildingAge(30L);
		p.setBuildingSqft("2221");
		p.setBuildingStoreys("1213");
		p.setBuildingType("Institute");
		p.setCostOfTheAsset(424244L);
		p.setPropertyType("PRIVATE");
		p.setSalvageValue(41424235L);
		p.setUsefulLifeOfTheAsset(424231L);
		
		lp.add(p);
		bu.setProperty(lp);
		
		
		List<BusinessDetails> bl = new ArrayList<>();
		bl.add(bu);
		
		consumer.setBusiness(bl);
		if(cService.checkEligibility(consumer))
		System.out.println(cService.saveConsumer(consumer));
		
		
	}

}
