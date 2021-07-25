package com.imo.consumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.imo.consumer.exception.ServiceResponse;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ResponseHandlers<T> {

	public <R> ResponseEntity<R> defaultResponse(T data){
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder().success(true).data(data).build();
		return new ResponseEntity<R>((R) responseModel, HttpStatus.OK);
	}
	
	public <R> ResponseEntity<R> defaultResponse(T data, String message){
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder().success(true).additionalMessage(message).data(data).build();
		return new ResponseEntity<R>((R) responseModel, HttpStatus.OK);
	}
	
	public <R> ResponseEntity<R> defaultResponse(T data, String message, HttpStatus status){
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder().success(true).additionalMessage(message).data(data).build();
		return new ResponseEntity<R>((R) responseModel, status);
	}
	
	public <R> ResponseEntity<R> defaultResponse(String message, HttpStatus status){
		ServiceResponse<T> responseModel = (ServiceResponse<T>) ServiceResponse.builder().success(true).additionalMessage(message).build();
		return new ResponseEntity<R>((R) responseModel, status);
	}
}
