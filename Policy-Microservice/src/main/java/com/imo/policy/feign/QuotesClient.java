package com.imo.policy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "quotes-service", url = "http://localhost:8123")
public interface QuotesClient {

	@GetMapping("/getQuotesForPolicy/{businessValue}/{propertyValue}/{propertyType}")
	public String getQuotesForPolicy(@PathVariable("businessValue") Long businessValue, @PathVariable("propertyValue") Long propertyValue,
			@PathVariable("propertyType") String propertyType);
	
}
