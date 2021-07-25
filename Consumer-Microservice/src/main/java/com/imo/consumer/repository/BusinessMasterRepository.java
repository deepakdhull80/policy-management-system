package com.imo.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imo.consumer.model.BusinessMaster;

public interface BusinessMasterRepository extends JpaRepository<BusinessMaster,Long> {
	BusinessMaster findByBusinessCategoryAndBusinessType(String businessCategory, String businessType);
}
