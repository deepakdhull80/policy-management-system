package com.imo.ui.service;

import org.springframework.stereotype.Service;

import com.imo.ui.exception.UnAuthorizedException;
import com.imo.ui.modal.CreateBusinessRequest;

@Service
public interface BusinessService {

	boolean addBusiness(CreateBusinessRequest data,String token)throws UnAuthorizedException;
	
}
