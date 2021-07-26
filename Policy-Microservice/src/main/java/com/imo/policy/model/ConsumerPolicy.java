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
@Table(name="Consumer_Policy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerPolicy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID")
	private Long id;
	
	@NotNull
	@Column(name="Policy_ID")
	private Long pid;
	
	@NotNull
	@Column(name="Business_ID")
	private Long businessId;
	
	@NotNull
	@Column(name="Consumer_ID")
	private Long consumerId;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Property_Type")
	private String propertyType;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Consumer_Type")
	private String consumerType;

	@NotBlank
	@Size(max = 40)
	@Column(name = "Assured_Sum")
	private String assuredSum;

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
	private String baseLocation;
	
	@NotBlank
	@Size(max = 30)
	@Column(name = "Type")
	private String type;
	
	@NotBlank
	@Size(max = 15)
	@Column(name = "Accepted_Quotes")
	private String acceptedQuote;
	
	@NotBlank
	@Size(max = 15)
	@Column(name = "Status")
	private String status;
	
	
}	