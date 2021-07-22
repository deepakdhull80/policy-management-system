package com.imo.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imo.consumer.model.BusinessDetails;

public interface BusinessRepository extends JpaRepository<BusinessDetails,Long>{

}
