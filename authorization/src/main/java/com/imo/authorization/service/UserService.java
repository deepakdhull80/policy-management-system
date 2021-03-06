package com.imo.authorization.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.imo.authorization.model.Roles;
import com.imo.authorization.model.User;
import com.imo.authorization.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loaduser method");
		return userRepository.findById(username).get();
		
	}
	
	public User createUser(User user,String role) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		Roles roles = new Roles(role);
		user.setRoles(List.of(roles) );
		return userRepository.save(user);
		
	}

}
