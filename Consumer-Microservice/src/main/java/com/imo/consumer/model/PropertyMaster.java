package com.imo.consumer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Property_Master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	private Long id;

	@NotBlank
	@Size(max = 30)
	@Column(name = "Insurance_Type")
	private String insuranceType;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Property_Type")
	private String propertyType;

	@NotBlank
	@Column(name = "Building_Type")
	private String buildingType;

	@NotNull
	@Column(name = "Building_Age")
	private Long buildingAge;
}
