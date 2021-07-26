package com.imo.policy.request;

import javax.validation.constraints.NotBlank;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerPolicyRequest {

	@NotNull
	private Long consumerId;
	
	private Long businessId;
	
	
}
