package com.imo.ui.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface PropertyService {
	
	Map<String,List<String>> getProperties(String token);
	
}
