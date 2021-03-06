package com.imo.policy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imo.policy.model.ConsumerDetails;
import com.imo.policy.model.ConsumerPolicy;

@Repository
public interface ConsumerPolicyRepository extends JpaRepository<ConsumerPolicy,Long> {
	public Optional<ConsumerPolicy> findByPid(Long pid);

	public ConsumerPolicy findByConsumerIdAndBusinessId(long cId, long bId);

	public List<ConsumerPolicy> findByBusinessId(Long bid);
}
