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
	
	private String businessCategory;

	private String businessType;

	private Long businessTurnOver;

	private Long capitalInvested;

	private Long totalEmployees;

	private Long businessValue;

	private Long businessAge;
	
	private List<PropertyDetails> property;

	@Override
	public String toString() {
		return "BusinessDetails [id=" + id + ", businessCategory=" + businessCategory + ", businessType=" + businessType
				+ ", businessTurnOver=" + businessTurnOver + ", capitalInvested=" + capitalInvested
				+ ", totalEmployees=" + totalEmployees + ", businessValue=" + businessValue + ", businessAge="
				+ businessAge + ", property=" + property + "]";
	}

	
	
}
