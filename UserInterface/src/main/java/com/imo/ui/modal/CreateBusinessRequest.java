package com.imo.ui.modal;

import lombok.Data;

@Data
public class CreateBusinessRequest {
	
	private long cid;
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
