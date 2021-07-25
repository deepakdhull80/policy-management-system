package com.imo.policy.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authorization", url = "http://localhost:8989/auth")
public interface AuthClient {
	@GetMapping("/get")
	public Map<Object,Object> getAuth();
}
