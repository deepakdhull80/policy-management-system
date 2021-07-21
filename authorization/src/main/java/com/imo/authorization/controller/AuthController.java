package com.imo.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imo.authorization.model.RequestUserBody;
import com.imo.authorization.model.User;
import com.imo.authorization.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/login")
	public ResponseEntity loginUser(@RequestBody RequestUserBody user) {
		System.out.println(user.getUsername()+" "+user.getPassword());
		try {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
				);
		
		User u = (User)authentication.getPrincipal();
		String token = jwtTokenUtil.generateToken(u);
		
		return ResponseEntity.ok().header("Authorization",token ).body(token);
		
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
}
