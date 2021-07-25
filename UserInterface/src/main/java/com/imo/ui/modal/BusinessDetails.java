package com.imo.ui.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDetails {
	private Long id;
	
	private String businesscategory;

	private String businesstype;

	private Long businessturnover;

	private Long capitalinvested;

	private Long totalemployees;

	private Long businessvalue;

	private Long businessage;
	
	private List<PropertyDetails> property;

	@Override
	public String toString() {
		return "BusinessDetails [id=" + id + ", businesscategory=" + businesscategory + ", businesstype=" + businesstype
				+ ", businessturnover=" + businessturnover + ", capitalinvested=" + capitalinvested
				+ ", totalemployees=" + totalemployees + ", businessvalue=" + businessvalue + ", businessage="
				+ businessage + ", property=" + property + "]";
	}
	
}
