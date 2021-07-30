package com.imo.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.imo.policy.model.PolicyMaster;
import com.imo.policy.service.PolicyService;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class PolicyMicroserviceApplication implements ApplicationRunner{

	@Autowired
	PolicyService service;
	
	public static void main(String[] args) {
		SpringApplication.run(PolicyMicroserviceApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		PolicyMaster master = new PolicyMaster();
		master.setId(123L);
		master.setPropertyType("PRIVATE");
		master.setConsumerType("Personal");
		master.setAssuredSum("150, 00000 INR");
		master.setTenure("123");
		master.setBusinessValue(12L);
		master.setBaseLocation("1213131");
		master.setType("Personal");
		master.setPropertyValue(0L);
		
		service.savePolicyMaster(master);
		
		master.setId(124L);
		master.setPropertyType("PRIVATE");
		master.setConsumerType("Personal");
		master.setAssuredSum("150, 00000 INR");
		master.setTenure("123");
		master.setBusinessValue(-3L);
		master.setBaseLocation("1213131");
		master.setType("Personal");
		master.setPropertyValue(20L);
		
		service.savePolicyMaster(master);
		
		
	}

}