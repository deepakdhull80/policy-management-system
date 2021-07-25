package com.imo.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imo.authorization.model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
