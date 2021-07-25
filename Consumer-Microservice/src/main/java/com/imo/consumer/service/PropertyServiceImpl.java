package com.imo.consumer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imo.consumer.repository.BusinessMasterRepository;
import com.imo.consumer.repository.PropertyMasterRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	BusinessMasterRepository busRepository;

	@Autowired
	PropertyMasterRepository proRepository;

	@Override
	public List<String> getBussinessCategory() {

		Set<String> result = busRepository.findAll().stream().map(bussiness -> bussiness.getBusinessCategory())
				.collect(Collectors.toSet());

		return new ArrayList<String>(result);

	}

	@Override
	public List<String> getBussinessType() {
		Set<String> result = busRepository.findAll().stream().map(bussiness -> bussiness.getBusinessType())
				.collect(Collectors.toSet());

		return new ArrayList<String>(result);
	}

	@Override
	public List<String> getInsuranceType() {
		Set<String> result = proRepository.findAll().stream().map(property -> property.getInsuranceType())
				.collect(Collectors.toSet());

		return new ArrayList<String>(result);
	}

	@Override
	public List<String> getPropertyType() {
		Set<String> result = proRepository.findAll().stream().map(property -> property.getPropertyType())
				.collect(Collectors.toSet());

		return new ArrayList<String>(result);
	}

	@Override
	public List<String> getBuildingType() {
		Set<String> result = proRepository.findAll().stream().map(property -> property.getBuildingType())
				.collect(Collectors.toSet());

		return new ArrayList<String>(result);
	}

}
