package com.imo.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imo.consumer.model.BusinessMaster;
import com.imo.consumer.model.PropertyMaster;

@Repository
public interface PropertyMasterRepository extends JpaRepository<PropertyMaster,Long> {
	PropertyMaster findByBuildingtypeAndPropertytype(String buildingtype, String propertytype);
}
