package com.imo.policy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name="business")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessDetails {
	@Id
	@Column(name ="ID")
	private Long id;
	
	@NotNull
	@Size(max = 40)
	@Column(name = "Business_Category")
	private String businessCategory;

	@NotNull
	@Size(max = 40)
	@Column(name = "Business_Type")
	private String businessType;

	@NotNull
	@Column(name = "Business_Turnover")
	private Long businessTurnOver;

	@NotNull
	@Column(name = "Capital_Invested ")
	private Long capitalInvested;

	@NotNull
	@Column(name = "Total_Employees")
	private Long totalEmployees;

	@NotNull
	@Column(name = "Business_Value")
	private Long businessValue;

	@NotNull
	@Column(name = "Business_Age")
	private Long businessAge;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "business_id")
	private List<PropertyDetails> property;
}
