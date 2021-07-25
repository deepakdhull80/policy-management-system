package com.imo.ui.modal;

import java.util.Date;

import lombok.Data;

@Data
public class CreateConsumerRequest {

	private String consumerName;
	private String dateOfBirth;
	private String panDetails;
	private String phone;
	private String email;
	private String agentName;
	private String businessCategory;
	private String businessType;
	private Long businessTurnover;
	private Long capitalInvested;
	private Long totalEmployee;
	private long businessAge;
	private String propertyType;
	private String buildingsqft;
	private String buildingType;
	private String buildingStoreys;
	private long buildingAge;
	private long costOfTheAsset;
	private long lifeOfAsset;
	private long salvageValue;
	
	
	
	
}
