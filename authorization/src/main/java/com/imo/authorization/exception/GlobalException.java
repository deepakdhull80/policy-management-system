package com.imo.authorization.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity expiredJwt() {
		Map<Object,Object> response = new HashMap<>();
		
		response.put("Error", "JWT Session Expired");
		
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}
	
	
}
