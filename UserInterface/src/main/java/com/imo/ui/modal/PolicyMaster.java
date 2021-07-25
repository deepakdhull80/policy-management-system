package com.imo.ui.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyMaster {
	
	private Long id;
	
	private String property_type;
	
	private String consumer_type;

	private String assured_sum;

	private String tenure;

	private Long businessValue;
	
	private Long propertyValue;
	
	private String base_location;
	
	private String type;

}
