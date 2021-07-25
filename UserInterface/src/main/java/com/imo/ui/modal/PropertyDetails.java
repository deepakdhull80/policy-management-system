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
	
	private String propertytype;

	private String buildingsqft;

	private String buildingtype;

	private String buildingstoreys;

	private Long buildingage;

	private Long propertyvalue;
	
	private Long costoftheasset;
	
	private Long usefullifeoftheAsset;

	private Long salvagevalue;

	@Override
	public String toString() {
		return "PropertyDetails [id=" + id + ", propertytype=" + propertytype + ", buildingsqft=" + buildingsqft
				+ ", buildingtype=" + buildingtype + ", buildingstoreys=" + buildingstoreys + ", buildingage="
				+ buildingage + ", propertyvalue=" + propertyvalue + ", costoftheasset=" + costoftheasset
				+ ", usefullifeoftheAsset=" + usefullifeoftheAsset + ", salvagevalue=" + salvagevalue + "]";
	}
	
	
	
}
