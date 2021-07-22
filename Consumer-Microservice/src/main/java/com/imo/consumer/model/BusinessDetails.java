package com.imo.consumer.model;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="business")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID")
	private Long id;
	
	@NotBlank
	@Size(max = 40)
	@Column(name = "Business_Category")
	private String businesscategory;

	@NotBlank
	@Size(max = 40)
	@Column(name = "Business_Type")
	private String businesstype;

	@NotNull
	@Column(name = "Business_Turnover")
	private Long businessturnover;

	@NotNull
	@Column(name = "Capital_Invested ")
	private Long capitalinvested;

	@NotNull
	@Column(name = "Total_Employees")
	private Long totalemployees;

	@NotNull
	@Column(name = "Business_Value")
	private Long businessvalue;

	@NotNull
	@Column(name = "Business_Age")
	private Long businessage;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "business_id")
	private List<PropertyDetails> property;

	@Override
	public String toString() {
		return "BusinessDetails [id=" + id + ", businesscategory=" + businesscategory + ", businesstype=" + businesstype
				+ ", businessturnover=" + businessturnover + ", capitalinvested=" + capitalinvested
				+ ", totalemployees=" + totalemployees + ", businessvalue=" + businessvalue + ", businessage="
				+ businessage + ", property=" + property + "]";
	}
	
}
