package com.imo.ui.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDetails {

	private Long id;

	private String name;

	private String dob;

	private String panDetails;

	private String email;

	private String phone;

	private String agentName;

	private List<BusinessDetails> business;

	@Override
	public String toString() {
		return "ConsumerDetails [id=" + id + ", name=" + name + ", dob=" + dob + ", panDetails=" + panDetails
				+ ", email=" + email + ", phone=" + phone + ", agentName=" + agentName + ", business=" + business + "]";
	}

	
	
	
}
