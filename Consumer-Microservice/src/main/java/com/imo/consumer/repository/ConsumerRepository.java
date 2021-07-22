package com.imo.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imo.consumer.model.ConsumerDetails;

@Repository
public interface ConsumerRepository extends JpaRepository<ConsumerDetails,Long> {

}
