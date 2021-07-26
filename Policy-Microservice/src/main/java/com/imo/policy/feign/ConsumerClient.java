package com.imo.policy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.imo.policy.exception.AuthorizationException;
import com.imo.policy.exception.ConsumerNotFoundException;
import com.imo.policy.model.ConsumerDetails;

@FeignClient(name = "consumer-service", url = "http://localhost:8100/")
public interface ConsumerClient {

	@GetMapping("/getconsumers/{cid}")
	public ConsumerDetails viewConsumer(
			@PathVariable Long cid)
			throws ConsumerNotFoundException, AuthorizationException;
}
