package com.imo.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imo.authorization.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
