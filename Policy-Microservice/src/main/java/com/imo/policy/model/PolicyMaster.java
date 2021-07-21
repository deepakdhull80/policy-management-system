package com.imo.policy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Policy_Master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;
	
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Property_Type")
	private String property_type;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Consumer_Type")
	private String consumer_type;

	@NotBlank
	@Size(max = 40)
	@Column(name = "Assured_Sum")
	private String assured_sum;

	@NotBlank
	@Size(max = 30)
	@Column(name = "Tenure")
	private String tenure;

	@NotNull
	@Column(name = "Business_Value")
	private Long businessValue;
	
	@NotNull
	@Column(name = "Property_Value")
	private Long propertyValue;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Base_Location")
	private String base_location;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Type")
	private String type;

}
