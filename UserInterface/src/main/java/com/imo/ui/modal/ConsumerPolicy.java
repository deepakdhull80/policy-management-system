package com.imo.ui.modal;


import lombok.Data;

@Data
public class ConsumerPolicy {
	
	private Long id;
	
	private Long pid;
	
	private Long businessId;
	
	private Long consumerId;
	
	private String propertyType;
	
	private String consumerType;

	private String assuredSum;

	private String tenure;

	private Long businessValue;
	
	private Long propertyValue;
	
	private String baseLocation;
	
	private String type;
	
	private String acceptedQuote;
	
	private String status;
	
	
}	