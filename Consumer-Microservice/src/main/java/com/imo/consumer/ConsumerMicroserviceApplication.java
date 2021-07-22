package com.imo.consumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.imo.consumer.model.BusinessMaster;
import com.imo.consumer.model.PropertyMaster;
import com.imo.consumer.repository.BusinessMasterRepository;
import com.imo.consumer.repository.PropertyMasterRepository;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ConsumerMicroserviceApplication implements ApplicationRunner{

	@Autowired
	BusinessMasterRepository busMasterRepository;
	
	@Autowired
	PropertyMasterRepository prRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerMicroserviceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		BusinessMaster businessMaster = new BusinessMaster(1L,"Permissible","Real Estate",45L,2L);
		
		busMasterRepository.save(businessMaster);
		
		PropertyMaster propertyMaster = new PropertyMaster(1L,"owned", "kk", "apartment", 12L);
		
		
	}

}
