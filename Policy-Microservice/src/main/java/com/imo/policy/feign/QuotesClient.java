package com.imo.policy.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "quotes-service", url = "http://localhost:8123")
public interface QuotesClient {

	@GetMapping("/getQuotesForPolicy/{businessValue}/{propertyValue}/{propertyType}")
	public String getQuotesForPolicy(@Valid @PathVariable Long businessValue, @PathVariable Long propertyValue,
			@PathVariable String propertyType);
	
}
