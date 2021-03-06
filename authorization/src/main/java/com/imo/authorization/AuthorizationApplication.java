package com.imo.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.imo.authorization.model.Roles;
import com.imo.authorization.model.User;
import com.imo.authorization.repository.UserRepository;
import com.imo.authorization.service.UserService;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class AuthorizationApplication implements ApplicationRunner{

	private static Logger LOGGER = LoggerFactory.getLogger(AuthorizationApplication.class);
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService service;
	
	public static void main(String[] args) {
		LOGGER.info("---------Authorization / Api Gateway Service Starts. ----------");
		
		SpringApplication.run(AuthorizationApplication.class, args);
		
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		User user = new User("agent1","password");
		
		service.createUser(user, "AGENT");
		LOGGER.info("---------------user created-----------");
		
		System.out.println(userRepository.findById("agent1").get());
		
	}
	
	

}
