package com.imo.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imo.quotes.models.QuotesMaster;

@Repository
public interface QuotesMasterRepository extends JpaRepository<QuotesMaster, Long> {

	QuotesMaster findByBusinessValueAndPropertyValueAndPropertyType(Long businessValue,Long propertyValue,String propertyType);
}
