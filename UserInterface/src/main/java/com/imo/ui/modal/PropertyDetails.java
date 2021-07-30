package com.imo.ui.modal;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDetails {

	private Long id;
	
	private String propertyType;

	private String buildingSqft;

	private String buildingType;

	private String buildingStoreys;

	private Long buildingAge;

	private Long propertyValue;
	
	private Long costOfTheAsset;
	
	private Long usefulLifeOfTheAsset;

	private Long salvageValue;
	
	ConsumerPolicy consumerPolicy;

	@Override
	public String toString() {
		return "PropertyDetails [id=" + id + ", propertyType=" + propertyType + ", buildingSqft=" + buildingSqft
				+ ", buildingType=" + buildingType + ", buildingStoreys=" + buildingStoreys + ", buildingAge="
				+ buildingAge + ", propertyValue=" + propertyValue + ", costOfTheAsset=" + costOfTheAsset
				+ ", usefulLifeOfTheAsset=" + usefulLifeOfTheAsset + ", salvageValue=" + salvageValue + "]";
	}

	
	
	
	
}
