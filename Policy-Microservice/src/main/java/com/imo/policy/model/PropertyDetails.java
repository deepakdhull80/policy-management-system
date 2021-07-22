package com.imo.policy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Property")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDetails {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Property_Type")
	private String propertyType;

	@NotBlank
	@Size(max = 30)
	@Column(name = "Building_Sqft")
	private String buildingSqft;

	@NotBlank
	@Size(max = 7)
	@Column(name = "Building_Type")
	private String buildingType;

	@NotBlank
	@Size(max = 10)
	@Column(name = "Building_Storeys")
	private String buildingstoreys;

	@NotNull
	@Column(name = "Building_Age")
	private Long buildingAge;

	@NotNull
	@Column(name = "Property_Value")
	private Long propertyValue;
	
	@NotNull
	@Column(name = "Cost_of_the_asset")
	private Long costOfTheAsset;
	
	@NotNull
	@Column(name = "Useful_Life_of_the_Asset")
	private Long usefulLifeOfTheAsset;

	@NotNull
	@Column(name = "Salvage_value")
	private Long salvageValue;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "policy_id")
	ConsumerPolicy consumerPolicy;
}
