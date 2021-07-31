package com.imo.ui.modal;

import lombok.Data;

@Data
public class PolicyMaster {
	
	private Long id;
	
	
	private String propertyType;
	
	private String consumerType;

	private String assuredSum;

	private String tenure;

	private Long businessValue;
	
	private Long propertyValue;
	
	private String baseLocation;
	
	private String type;

}
